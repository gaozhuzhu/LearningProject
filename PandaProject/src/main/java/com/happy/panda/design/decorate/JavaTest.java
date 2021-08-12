package com.happy.panda.design.decorate;

import android.util.Log;

import com.happy.panda.design.impl.BoBoTea;
import com.happy.panda.design.impl.OriginalTea;
import com.happy.panda.design.impl.PearlTea;

public class JavaTest {

    public static void main(String[] args) {

        OriginalTea originalTea = new OriginalTea();
        System.out.println("Tea1-----" + originalTea.toString());

        PearlTea pearlTea = new PearlTea(new OriginalTea());
        System.out.println("Tea3-----" + pearlTea.toString());

        BoBoTea boBoTea = new BoBoTea(new OriginalTea());
        System.out.println("Tea2-----" + boBoTea.toString());

    }
}
