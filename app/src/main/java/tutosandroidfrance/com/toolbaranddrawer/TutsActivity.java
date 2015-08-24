package tutosandroidfrance.com.toolbaranddrawer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tutosandroidfrance.com.toolbaranddrawer.tuts.CirclePageIndicator;
import tutosandroidfrance.com.toolbaranddrawer.tuts.FirstFragment;
import tutosandroidfrance.com.toolbaranddrawer.tuts.FourthFragment;
import tutosandroidfrance.com.toolbaranddrawer.tuts.SecondFragment;
import tutosandroidfrance.com.toolbaranddrawer.tuts.SharedDataUtils;
import tutosandroidfrance.com.toolbaranddrawer.tuts.ThirdFragment;


public class TutsActivity extends FragmentActivity implements View.OnClickListener {
   

        private FragmentPagerAdapter adapterViewPager;
        private CirclePageIndicator myindicator;
        private TextView skipIntro;
        private SharedDataUtils dataUtils;
        private SharedPreferences appPreferences;
        private ViewPager vpPager;
        private Button mSignInBtn, mSignUpBtn;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


            setContentView(R.layout.tuts);
            dataUtils = new SharedDataUtils(getApplicationContext());
            myindicator = (CirclePageIndicator) findViewById(R.id.titles);
            vpPager = (ViewPager) findViewById(R.id.vpPager);
            mSignInBtn= (Button)findViewById(R.id.sign_in);
         //   mSignUpBtn= (Button)findViewById(R.id.sign_up);


            mSignInBtn.setOnClickListener(this);
           // mSignUpBtn.setOnClickListener(this);
            // adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
            adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
            vpPager.setAdapter(adapterViewPager);
            myindicator.setViewPager(vpPager);

//            if (!dataUtils.isShowTutori/al()) {
//                Intent i = new Intent(HomeActivity.this, SignUpActivity.class);
//                startActivity(i);
//                finish();
//
//
//            }


//        skipIntro.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//
//                myindicator.setCurrentItem(4);
//
//
//            }
//        });
            // Attach the page change listener inside the activity
            myindicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                // This method will be invoked when a new page becomes selected.
                @Override
                public void onPageSelected(int position) {
                    //myindicator.setPageColor(position);


                }

                // This method will be invoked when the current page is scrolled
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    // Code goes here
                    // visible(position);
                }

                // Called when the scroll state changes:
                // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
                @Override
                public void onPageScrollStateChanged(int state) {
                    // Code goes here
                }
            });
        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.sign_in:
                    startActivity(new Intent(TutsActivity.this, MainActivity.class));
                    finish();
                    break;

                case R.id.sign_up:
                    startActivity(new Intent(TutsActivity.this, MainActivity.class));
                    finish();
                    break;
                default:
                    break;

            }
        }

        public static class MyPagerAdapter extends FragmentPagerAdapter {


            private static int NUM_ITEMS = 4;

            Fragment f1, f2, f3, f4;

            public MyPagerAdapter(FragmentManager fragmentManager) {
                super(fragmentManager);
                f1 = FirstFragment.newInstance();
                f2 = SecondFragment.newInstance();
                f3 = ThirdFragment.newInstance();
                f4 = FourthFragment.newInstance();

            }

            // Returns total number of pages
            @Override
            public int getCount() {
                return NUM_ITEMS;
            }

            // Returns the fragment to display for that page
            @Override
            public Fragment getItem(int position) {

                switch (position) {
                    case 0: // Fragment # 0 - This will show FirstFragment

                        return f1;
                    case 1: // Fragment # 0 - This will show FirstFragment different title

                        return f2;
                    case 2: // Fragment # 1 - This will show SecondFragment

                        return f3;
                    case 3: // Fragment # 1 - This will show SecondFragment

                        return f4;

                    default:
                        return null;
                }
            }

            // Returns the page title for the top indicator
            @Override
            public CharSequence getPageTitle(int position) {

                return "Page " + position;
            }

        }

        //    public void visible(int p) {
//        if(p==2){
//
//            .setVisibility(View.GONE);
//        }
//        else if(p==3)
//        {
//            skipIntro.setVisibility(View.GONE);
//        }else if(p==4){
//            skipIntro.setVisibility(View.GONE);
//        }
//
//        else{
//            skipIntro.setVisibility(View.VISIBLE);
//        }
//
//    }
        @Override
        public void onStart() {
            super.onStart();
            //FlurryAgent.onStartSession(this, Constants.FLURRY_KEY);


        }

        @Override
        protected void onStop() {
            // TODO Auto-generated method stub
            super.onStop();
            //FlurryAgent.onEndSession(this);

        }
    }