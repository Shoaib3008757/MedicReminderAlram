package rdm.medicimalarm;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import rdm.medicimalarm.DaysFragment.DayFive;
import rdm.medicimalarm.DaysFragment.DayFour;
import rdm.medicimalarm.DaysFragment.DayOne;
import rdm.medicimalarm.DaysFragment.DaySeven;
import rdm.medicimalarm.DaysFragment.DaySix;
import rdm.medicimalarm.DaysFragment.DayThree;
import rdm.medicimalarm.DaysFragment.DayTwo;

/**
 * Created by User-10 on 10-Nov-17.
 */

public class PageFragmentAdapter extends FragmentStatePagerAdapter {

    int mNumberOfTabs;

    public PageFragmentAdapter(FragmentManager fm, int NumberOfTabs){
        super(fm);
        this.mNumberOfTabs = NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position){

        switch (position){
            case  0:
                DayOne pageOne = new DayOne();
                return pageOne;
            case 1:

                DayTwo pageTwo = new DayTwo();
                return pageTwo;
            case 2:
                DayThree pageThree = new DayThree();
                return pageThree;

            case 3:
                DayFour pageFour = new DayFour();
                return pageFour;
            case 4:
                DayFive PageFive = new DayFive();
                return PageFive;
            case 5:
                DaySix pageSix = new DaySix();
                return pageSix;
            case 6:
                DaySeven pageSeven = new DaySeven();
                return pageSeven;

            default:
                DayOne pageOneDefault = new DayOne();
                return pageOneDefault;
        }
    }

    @Override
    public int getCount(){
        return mNumberOfTabs;
    }
}
