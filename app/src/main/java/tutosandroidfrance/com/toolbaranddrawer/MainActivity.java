package tutosandroidfrance.com.toolbaranddrawer;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private View button;
    private LinearLayout layout;
    private LayoutInflater inflater;
    private TextView profileTV, aboutusTV, rateUsTV;

    private LinearLayout drawerlist;
    private DrawerLayout drawerLayout;
    private ScrollView scrollNav;
    private ActionBarDrawerToggle drawerToggle;
//Raj


    private List<String> listDataHeader, listDataHeader1;
    private HashMap<String, List<String>> listDataChild, listDataChild1;


    ArrayList<String> groupItem = new ArrayList<String>();
    ArrayList<Object> childItem = new ArrayList<Object>();
    //Raj


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.toolbar = (Toolbar) findViewById(R.id.too1lbar);


        scrollNav = (ScrollView) findViewById(R.id.scrollNav);
//        retExpListView = (ExpandableListView) findViewById(R.id.retExLV);
//        catExpListView = (ExpandableListView) findViewById(R.id.catExLV);
//        profileTV = (TextView) findViewById(R.id.profile);
//        aboutusTV = (TextView) findViewById(R.id.aboutus);
//        rateUsTV = (TextView) findViewById(R.id.rateus);
//       drawerlist = (LinearLayout) findViewById(R.id.drawerlist);
//        profileTV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Profile", Toast.LENGTH_LONG).show();
//                closeDrawer();
//            }
//        });
//
//        rateUsTV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Rate Us", Toast.LENGTH_LONG).show();
//                closeDrawer();
//            }
//        });
//        aboutusTV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "About Us", Toast.LENGTH_LONG).show();
//                closeDrawer();
//            }
//        });
//        prepareListData();
//        prepareListData2();
//
//        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
//        catExpListView.setAdapter(new NewAdapter(this, groupItem, childItem));
//       // catExpListView.setAdapter(listAdapter);
//
//////
////        listAdapter1 = new ExpandableListAdapter1(this, listDataHeader1, listDataChild1);
//        retExpListView.setAdapter(new NewAdapter(this, groupItem, childItem));
//        setOnCatclick();
//        setOnRetClick();


        //definir notre toolbar en tant qu'actionBar
        setSupportActionBar(toolbar);

        //afficher le bouton retour
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);


        drawerToggle = new ActionBarDrawerToggle(this, this.drawerLayout, toolbar, 0, 0) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                scrollNav.bringToFront();
                scrollNav.requestLayout();
                // catExpListView.requestFocus();
                //getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        this.drawerLayout.setDrawerListener(drawerToggle);


        //  this.button = findViewById(R.id.button);
//        this.button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                afficherCacherToolbar();
//            }
//        });

        layout = (LinearLayout) findViewById(R.id.layout);
        inflater = getLayoutInflater();
        displayData();
    }

    private ArrayList<MenuDatum> parseData() {
        try {
            String json = readJSON();

         //   Gson gson = new Gson();

            //  MenuDatum menuDatum = gson.fromJson(json, MenuDatum.class);

            JSONArray js = new JSONArray(json);
            ArrayList<MenuDatum> menuDatums = new ArrayList<MenuDatum>();
            ArrayList<Child> chlids;
            for (int i = 0; i < js.length(); i++) {
                MenuDatum datum = new MenuDatum();
                JSONObject jsonobj = js.getJSONObject(i);
                datum.setName(jsonobj.getString("name"));
                datum.setHref(jsonobj.getString("href"));
                datum.setColumn(jsonobj.getString("column"));
                datum.setImage(jsonobj.getString("image"));

                JSONArray ddt = jsonobj.getJSONArray("children");
                chlids = new ArrayList<Child>();
                for (int j = 0; j < ddt.length(); j++) {
                    Child child = new Child();

                    child.setName(ddt.getJSONObject(j).getString("name"));
                    child.setHref(ddt.getJSONObject(j).getString("href"));
                    child.setImage(ddt.getJSONObject(j).getString("image"));
                    chlids.add(child);


                }
                datum.setChildren(chlids);

                menuDatums.add(datum);

            }


           //HashMap<String, ArrayList<Item>> map = new HashMap<>();
//            JSONObject jObject = new JSONObject(json);
//            Iterator<?> keys = jObject.keys();
//
//            while (keys.hasNext()) {
//                String key = (String) keys.next();
//                JSONArray array = jObject.getJSONArray(key);
//                map.put(key, parseArray(array));
//            }

            return menuDatums;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    private ArrayList<Item> parseArray(JSONArray array) {
//        try {
//            ArrayList<Item> arrayList = new ArrayList<>();
//            for (int i = 0; i < array.length(); i++) {
//                JSONObject jsonObject = array.getJSONObject(i);
//                Item item = new Item();
//                item.title = jsonObject.getString("title").toUpperCase();
//                if (jsonObject.has("childs")) {
//                    item.childs = parseArray(jsonObject.getJSONArray("childs"));
//
//
//                    System.out.println('"' + "Welcome java" + '"');
//                }
//                arrayList.add(item);
//
//            }
//            return arrayList;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    private void displayData() {
        // HashMap<String, ArrayList<Item>> map = parseData();


        ArrayList<MenuDatum> menuDatums = parseData();

        for (MenuDatum menuDatum : menuDatums) {

            View row = inflater.inflate(R.layout.list_item, null);
            TextView txt = (TextView) row.findViewById(R.id.txt);
            txt.setText(menuDatum.getName());


            LinearLayout linearLayout = addChild(menuDatum);

           // layout.addView(row);
            layout.addView(linearLayout);
        }
    }

    private LinearLayout addChild(MenuDatum menuDatum) {
        LinearLayout linearLayout = new LinearLayout(this);
        if (menuDatum != null) {

            linearLayout.setOrientation(LinearLayout.VERTICAL);
            View rowChild = inflater.inflate(R.layout.list_item, null);
            TextView txtChild = (TextView) rowChild.findViewById(R.id.txt);
            final TextView plus = (TextView) rowChild.findViewById(R.id.plus);
            final TextView minus = (TextView) rowChild.findViewById(R.id.minus);
//                View rowChild = inflater.inflate(R.layout.list_item_child, null);
//                TextView txtChild = (TextView) rowChild.findViewById(R.id.txtChild);
                txtChild.setText(menuDatum.getName());
                linearLayout.addView(rowChild);
                if (menuDatum.getChildren()!= null) {
                    final LinearLayout linearLayoutChild =new LinearLayout(this);
                    linearLayoutChild.setOrientation(LinearLayout.VERTICAL);
                    linearLayoutChild.setVisibility(View.GONE);
                 for(final Child child: menuDatum.getChildren()) {
                     final LinearLayout linearLayoutChild1 =new LinearLayout(this);
                     View subChild = inflater.inflate(R.layout.list_item_child, null);
                     TextView txtsubChild = (TextView) subChild.findViewById(R.id.txtChild);
                     txtsubChild.setText(child.getName());
                     linearLayoutChild1.addView(subChild);
                     linearLayoutChild.addView(linearLayoutChild1);

                     txtsubChild.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             drawerLayout.closeDrawer(scrollNav);
                             Toast.makeText(getApplicationContext(),""+child.getHref(),Toast.LENGTH_LONG).show();
                         }
                     });

                 }
                    linearLayout.addView(linearLayoutChild);
                     txtChild.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (linearLayoutChild.getVisibility() == View.VISIBLE) {
                            linearLayoutChild.setVisibility(View.GONE);
                            minus.setVisibility(View.GONE);
                            plus.setVisibility(View.VISIBLE);
                        } else {
                            linearLayoutChild.setVisibility(View.VISIBLE);
                            minus.setVisibility(View.VISIBLE);
                            plus.setVisibility(View.GONE);
                        }
                    }
                });


                } else {
                    txtChild.setText(menuDatum.getName());
                }

            }
        return linearLayout;


    }

    private String readJSON() {
        InputStream raw = getResources().openRawResource(R.raw.menudata);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i;
        try {
            i = raw.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = raw.read();
            }
            raw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toString();
    }


    //ne pas oublier de copier/coller ces 2 méthodes
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // synchroniser le drawerToggle après la restauration via onRestoreInstanceState
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void afficherCacherToolbar() {
//        if (toolbar.getAlpha() == 1) { //si alpha==1 alors elle est affichee
//
//            //cacher
//            toolbar.animate()
//                    .alpha(0) //la rendre invisible
//                    .translationY(-toolbar.getHeight()) //la déplacer vers le haut
//                    .start();
//        } else { //si alpha==0 alors elle est cachee
//
//            //afficher
//            toolbar.animate()
//                    .alpha(1) //la rendre visible
//                    .translationY(0) //retour à la position d'origine
//                    .start();
//        }
    }


    /*
     * Preparing the list data
     */


    private void closeDrawer() {
        if (this.drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            this.drawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            this.drawerLayout.openDrawer(Gravity.LEFT);
        }
    }
}
