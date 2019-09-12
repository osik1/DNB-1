package com.i_project.dnb.Adapters;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.i_project.dnb.Fragments.ILibraryFragment;
import com.i_project.dnb.Fragments.PasscoFragment;
import com.i_project.dnb.R;

public class DnbILibraryFragmentAdapter extends FragmentPagerAdapter {

    Context contextExtends;

    public DnbILibraryFragmentAdapter(Context context , FragmentManager fm) {
        super(fm);
        this.contextExtends = context;
    }

    @Override
    public Fragment getItem(int i) {
       if (i == 0){
           return new PasscoFragment();
       }else if (i == 1){
           return new ILibraryFragment();
       }
       return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return contextExtends.getString(R.string.Passco);
            case 1:
                return contextExtends.getString(R.string.I_Library_books);
                default:
                    return null;
        }
    }
}
