package com.example.dbn.Adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dbn.Fragments.DepartmentUpdateFragment;
import com.example.dbn.Fragments.LevelFourHundredFragment;
import com.example.dbn.Fragments.LevelHundredFragment;
import com.example.dbn.Fragments.LevelThreeHundredFragment;
import com.example.dbn.Fragments.LevelTwoHundredFragment;
import com.example.dbn.R;

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

        if (fraposition == 0){
            return new  LevelHundredFragment();
        }else if(fraposition == 1){
            return new LevelTwoHundredFragment();
        }else if (fraposition == 2){
            return new LevelThreeHundredFragment();
        }else if (fraposition == 3){
            return new LevelFourHundredFragment();
        }else if (fraposition == 4){
            return new DepartmentUpdateFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
                return contextExtend.getString(R.string.Level_hundred);
            case 1:
                return contextExtend.getString(R.string.Level_two_hundred);
            case 2:
                return contextExtend.getString(R.string.Level_three_hundred);
            case 3:
                return contextExtend.getString(R.string.Level_four_hundred);
            case 4:
                return contextExtend.getString(R.string.depart_update);
                default:
                    return null;
        }
    }
}
