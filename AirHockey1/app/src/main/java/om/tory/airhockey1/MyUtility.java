package om.tory.airhockey1;

import android.content.Context;
import android.widget.Toast;

public class MyUtility {
    public static void Toast (Context context, String string) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
    }

    public static void Toastt (Context context, String string) {
        //Do nothing which used to disable the toast.
    }

}
