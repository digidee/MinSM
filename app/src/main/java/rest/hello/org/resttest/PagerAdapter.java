package rest.hello.org.resttest;

/**
 * Created by digi on 16.11.2015.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    String im;

    public PagerAdapter(FragmentManager fm, int NumOfTabs, String im) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.im = im;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TabFragmentInfo tab1 = new TabFragmentInfo();
                Bundle bundle = new Bundle();
                bundle.putString("im", im);
                tab1.setArguments(bundle);
                return tab1;
            case 1:
                TabFragmentUpdate tab2 = new TabFragmentUpdate();
                Bundle bundle2 = new Bundle();
                bundle2.putString("im", im);
                tab2.setArguments(bundle2);
                return tab2;
            case 2:
                TabFragmentResolve tab3 = new TabFragmentResolve();
                Bundle bundle3 = new Bundle();
                bundle3.putString("im", im);
                tab3.setArguments(bundle3);
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}