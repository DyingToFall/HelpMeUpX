package com.dyingtofall.helpmeupx;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * Created by James W on 9/12/2017.
 */

public class DialogDismisser {
    /**
     * Dismiss all DialogFragments added to given FragmentManager and child fragments
     */
    public static void dismissAllDialogs(FragmentManager manager) {
        List<Fragment> fragments = manager.getFragments();

        if (fragments == null)
            return;

        for (Fragment fragment : fragments) {
            if (fragment instanceof DialogFragment) {
                DialogFragment dialogFragment = (DialogFragment) fragment;
                dialogFragment.dismissAllowingStateLoss();
            }

            FragmentManager childFragmentManager = fragment.getChildFragmentManager();
            if (childFragmentManager != null)
                dismissAllDialogs(childFragmentManager);
        }
    }
}
