package ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by admin on 2017/11/17.
 */

public class NewsViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment>  listFragments;

    public NewsViewPagerAdapter(FragmentManager fm, List<Fragment> list){
        super(fm);
        this.listFragments=list;
    }
    @Override
    public Fragment getItem(int position) {
        return listFragments.get(position);
    }

    @Override
    public int getCount() {
        return listFragments.size();
    }
}
