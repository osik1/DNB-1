package com.i_project.dnb.Adapters;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.i_project.dnb.Fragments.LevelFourHundredFragment;
import com.i_project.dnb.Fragments.LevelHundredFragment;
import com.i_project.dnb.Fragments.LevelThreeHundredFragment;
import com.i_project.dnb.Fragments.LevelTwoHundredFragment;
import com.i_project.dnb.R;

public class DnbFragmentAdapter extends FragmentPagerAdapter {

    //==============================================//
    // this extends the context of the fragment    //
    //============================================//
    public Context contextExtend;

    public DnbFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);

        this.contextExtend = context;
    }

    //===============================================//
    // this part gets the position of each fragment //
    // ============================================//
    @Override
    public Fragment getItem(int fraposition) {

        if (fraposition == 0) {
            return new LevelHundredFragment();
        } else if (fraposition == 1) {
            return new LevelTwoHundredFragment();
        } else if (fraposition == 2) {
            return new LevelThreeHundredFragment();
        } else if (fraposition == 3) {
            return new LevelFourHundredFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return contextExtend.getString(R.string.Level_hundred);

            case 1:
                return contextExtend.getString(R.string.Level_two_hundred);

            case 2:
                return contextExtend.getString(R.string.Level_three_hundred);

            case 3:
                return contextExtend.getString(R.string.Level_four_hundred);

            default:
                return null;
        }
    }
}
