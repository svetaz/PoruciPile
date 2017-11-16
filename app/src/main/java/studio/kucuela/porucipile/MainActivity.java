package studio.kucuela.porucipile;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.clans.fab.FloatingActionButton;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;




public class MainActivity extends AppCompatActivity {

    Spinner sp7;
    ArrayAdapter<String> adapter7;
    String numbers7[] = {"[nema unosa]", "1", "2", "3", "4", "Probilo!"};

    Spinner sp8;
    ArrayAdapter<String> adapter8;
    String numbers8[] = {"[nema unosa]", "More", "Oblaci", "Olujno more", "Olujni oblaci"};

    Spinner sp9;
    ArrayAdapter<String> adapter9;
    String numbers9[] = {"[nema unosa]", "Plava", "Bela", "Žuta", "Narandžasta", "Još uvek je dan"};






    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private FloatingActionButton fab3;
    private FloatingActionButton fab4;
    private FloatingActionButton fab5;
    private FloatingActionButton fab6;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //startService(new Intent(this, MyService.class));








        // Enables ActionBar app icon to behave as action to toggle NavigationDrawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            //actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
            actionBar.setHomeButtonEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled( true );
            getSupportActionBar().setTitle("Poruči pile");
            getSupportActionBar().setSubtitle("app za maltretiranje kolege Bojana");

            actionBar.show();
        }




        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        fab4 = (FloatingActionButton) findViewById(R.id.fab4);
        fab5 = (FloatingActionButton) findViewById(R.id.fab5);
        fab6 = (FloatingActionButton) findViewById(R.id.fab6);



        fab1.setOnClickListener(clickListener);
        fab2.setOnClickListener(clickListener);
        fab3.setOnClickListener(clickListener);
        fab4.setOnClickListener(clickListener);
        fab5.setOnClickListener(clickListener);
        fab6.setOnClickListener(clickListener);



    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        stopService(new Intent(this, MyService.class));



    }

    @Override
    protected void onPause() {

        super.onPause();
        stopService(new Intent(this, MyService.class));


    }















    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab1://poruci pile

                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                    View mView = getLayoutInflater().inflate(R.layout.dialog_spinner,null);

                    final Spinner mSpinnerIme = (Spinner) mView.findViewById(R.id.spinner);
                    final Spinner mSpinnerKolicina = (Spinner) mView.findViewById(R.id.spinner2);
                    final Spinner mSpinnerNapomene = (Spinner) mView.findViewById(R.id.spinner3);

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,
                            getResources().getStringArray(R.array.imena));
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            mSpinnerIme.setAdapter(adapter);

                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,
                            getResources().getStringArray(R.array.kolicina));
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpinnerKolicina.setAdapter(adapter2);

                    ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,
                            getResources().getStringArray(R.array.napomene));
                    adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpinnerNapomene.setAdapter(adapter3);


                    mBuilder.setPositiveButton("POŠALJI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                            String date = String.valueOf(android.text.format.DateFormat.format("EE,dd.MMMM, HH:mm", new java.util.Date()));
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_SUBJECT, "Narudžbenica za piliće");
                            intent.putExtra(Intent.EXTRA_TEXT, "\n\nDATUM PORUDŽBINE\n"+date+"\n\nNARUČILAC:" + "\n"+mSpinnerIme.getSelectedItem().toString()
                                    +"\n\nKOLIČINA:" + "\n"+mSpinnerKolicina.getSelectedItem().toString()+"\n\nNAPOMENE:" + "\n"+mSpinnerNapomene.getSelectedItem().toString()+"\n\n"
                            );
                                    intent.setData(Uri.parse("mailto:bojanbatinic77@gmail.com")); // or just "mailto:" for blank
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                            startActivity(intent);








                        }
                    });

                    mBuilder.setNegativeButton("ODUSTANI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    mBuilder.setView(mView);
                    AlertDialog dialog = mBuilder.create();
                    dialog.show();


                    break;
                case R.id.fab2:
                    Intent intent1 = new Intent(MainActivity.this, MapsActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.fab3:
                    /*Uri gmmIntentUri = Uri.parse("geo:45.267136,19.833549");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    if (mapIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(mapIntent);*//*
                    }*/


                    try{

                        // your intent here
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:<45.302112>,<19.259502>?q=<45.302112>,<19.259502>(Prodaja+domaćih+pilića)"));
                        startActivity(intent);

                    } catch (ActivityNotFoundException e) {
                        // show message to user
                        Toast.makeText(MainActivity.this, "Nemate instaliran GoogleMaps!", Toast.LENGTH_SHORT).show();
                    }






                    break;
                case R.id.fab4:

                     FloatingActionButton button = (FloatingActionButton) findViewById(R.id.fab4);
                    button.setVisibility(View.GONE);

                    startService(new Intent(MainActivity.this, MyService.class));

                     FloatingActionButton button1 = (FloatingActionButton) findViewById(R.id.fab5);
                    button1.setVisibility(View.VISIBLE);




                    break;

                case R.id.fab5:

                     FloatingActionButton button3 = (FloatingActionButton) findViewById(R.id.fab5);
                     button3.setVisibility(View.GONE);

                    stopService(new Intent(MainActivity.this, MyService.class));

                    FloatingActionButton button4 = (FloatingActionButton) findViewById(R.id.fab4);
                    button4.setVisibility(View.VISIBLE);



                    break;

                case R.id.fab6:

                    String url = "https://github.com/svetaz";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);



                    break;


            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {

            new MaterialStyledDialog.Builder(this)
                    .setTitle("O APLIKACIJI")
                    .setDescription("Poruči pile je aplikacija namenjena zajebanciji koja je kolegi Bojanu i meni pala na pamet dok smo se vraćali kući sa predavanja,a kako smo tog dana učili o android lokacijama,došli smo na ideju da u to sve umuljamo i neke stvari sa tog časa." +
                            "Ako neko pak stvarno želi da kupi pile,moramo Vas razočarati jer su svi pilići prodati pre par dana.Aplikacija je verovatno puna bagova,pa slobodno šaljite kritike i zamerke,nećemo ih ni pročitati.")
                    .setIcon(R.drawable.pileu)

                    .setHeaderDrawable(R.drawable.laza)
                    //.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_launcher))

                    .setPositiveText("OK")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                            dialog.dismiss();
                        }})


                    .show();




            return true;
        }




        return super.onOptionsItemSelected(item);
    }


}