package dd.com.myq.Activity;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.HashMap;

import dd.com.myq.Fragment.AccountFragment;
import dd.com.myq.Fragment.FriendFragment;
import dd.com.myq.Fragment.HomeFragment;
import dd.com.myq.Fragment.PointFragment;
import dd.com.myq.R;
import dd.com.myq.Util.SessionManager;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity implements AccountFragment.OnFragmentInteractionListener, FriendFragment.OnFragmentInteractionListener, PointFragment.OnFragmentInteractionListener, HomeFragment.OnFragmentInteractionListener {

    public static String TAG = "Home";
    private RelativeLayout Home, Point, Account, Friends, Levels, Categories;
    private SessionManager sessionManager;
    private TextView UserName;
    private CircleImageView ProfilePicture;

    private static GoogleAnalytics sAnalytics;
    private static Tracker sTracker;

    private ImageLoader imageLoader;
    private DisplayImageOptions options;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getUserDetails();

        UserName = (TextView) findViewById(R.id.user_name);
        ProfilePicture = (CircleImageView) findViewById(R.id.profile_picture);
        Home = (RelativeLayout) findViewById(R.id.home_tab);
        Point = (RelativeLayout) findViewById(R.id.points_tab);
        Account = (RelativeLayout) findViewById(R.id.account_tab);
        Friends = (RelativeLayout) findViewById(R.id.friends_tab);
        Levels = (RelativeLayout) findViewById(R.id.level_container);
        Categories = (RelativeLayout) findViewById(R.id.category_container);

        UserName.setText(user.get(SessionManager.KEY_USERNAME));

        // Obtain the shared Tracker instance.
//        AnalyticsApplication application = (AnalyticsApplication) getApplication();
//        sTracker = application.getDefaultTracker();

//        imageLoader = ImageLoader.getInstance();
//
//        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
//
//        options = new DisplayImageOptions.Builder().cacheInMemory(true)
//                .cacheOnDisk(true).build();
//
//        imageLoader.displayImage(Config.defaultImagePrefix+ user.get(SessionManager.KEY_PROFILE_PIC), ProfilePicture, options);

        Levels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Coming Soon!", Toast.LENGTH_SHORT).show();
            }
        });

        Categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Coming Soon!", Toast.LENGTH_SHORT).show();
            }
        });

        Point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TAG = "Point";
                Bundle bundle=new Bundle();
                bundle.putString("flag", "10");

                Fragment fragment = new PointFragment();
                fragment.setArguments(bundle);
                FragmentManager manager = getSupportFragmentManager();

                FragmentTransaction transaction = manager.beginTransaction();
                transaction.setCustomAnimations(android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right);
                transaction.replace(R.id.main_container, fragment, TAG);
                transaction.addToBackStack(TAG);
                transaction.commit();
            }
        });

        Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TAG = "Account";
//                getSupportFragmentManager().popBackStackImmediate ();

                for(int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); ++i) {
                    getSupportFragmentManager().popBackStack();
                }

                Fragment fragment = new AccountFragment();
                FragmentManager manager = getSupportFragmentManager();
//
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.setCustomAnimations(android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right);
                Bundle bundle=new Bundle();
                bundle.putString("flag", "10");
                fragment.setArguments(bundle);

                transaction.replace(R.id.main_container, fragment, TAG);
                transaction.addToBackStack(TAG);
                transaction.commit();
            }
        });

        Friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TAG = "Friends";

                for(int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); ++i) {
                    getSupportFragmentManager().popBackStack();
                }
//
                Fragment fragment = new FriendFragment();
                FragmentManager manager = getSupportFragmentManager();

                FragmentTransaction transaction = manager.beginTransaction();

                Bundle bundle=new Bundle();
                bundle.putString("flag", "10");
                fragment.setArguments(bundle);

                transaction.setCustomAnimations(android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right);
                transaction.replace(R.id.main_container, fragment, TAG);

                transaction.addToBackStack(TAG);

                transaction.commit();
            }
        });

        Home.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TAG = "Home";
                Bundle bundle=new Bundle();
                bundle.putString("flag", "10");
//                getSupportFragmentManager().popBackStackImmediate ();
//
                for(int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); ++i) {
                    getSupportFragmentManager().popBackStack();
                }

                Fragment fragment = new HomeFragment();
                FragmentManager manager = getSupportFragmentManager();
                manager.popBackStack(TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                FragmentTransaction transaction = manager.beginTransaction();

                transaction.setCustomAnimations(android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right);
//                transaction.replace(R.id.main_container, fragment, TAG);
//                 fragment = getFragmentManager().findFragmentByTag(TAG);
                //Fragment prev_fragment = manager.findFragmentByTag(TAG);


                transaction.replace(R.id.main_container, fragment, TAG);

                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        Fragment fragment = new HomeFragment();
        fragment.setRetainInstance(true);
        FragmentManager manager = this.getSupportFragmentManager();


        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);
        transaction.replace(R.id.main_container, fragment, TAG);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {

        if (getFragmentManager().findFragmentByTag("Home") != null) {



        } else {

            // Otherwise, ask user if he wants to leave :)
            new AlertDialog.Builder(this)
                    .setTitle("MyQ says: ")
                    .setMessage("Are you sure you want to exit?")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            // MainActivity.super.onBackPressed();
                            finish();
                            moveTaskToBack(true);
                        }
                    }).create().show();

        }

    }


}