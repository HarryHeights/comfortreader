package com.mschlauch.comfortreader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;
/**
 This file is part of Comfort Reader.

 LICENSE
 Copyright 2014-2017 Michael Schlauch

 Comfort Reader is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 Comfort Reader is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with Comfort Reader.  If not, see <http://www.gnu.org/licenses/>.>.
 */


import android.widget.Toast;

import java.io.File;

public class BroadcastReceiverTest1Activity extends Activity {
    private String TAG = "TagOpenTxt";
    private String uri ="";
    private Uri uri2;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      //  setContentView(R.layout.activity_main);
        final Intent intent = getIntent();
        final String action = intent.getAction();

        if(Intent.ACTION_VIEW.equals(action)){
            //uri = intent.getStringExtra("URI");
            uri2 = intent.getData();
            uri = uri2.getPath() + ":";

            File myFile = new File(uri2.getPath());
           uri =  myFile.getAbsolutePath();
            Log.i("TextBroadcaster", "loaded: " + uri);


            SettingsLoader settingsload = new SettingsLoader (PreferenceManager.getDefaultSharedPreferences(this));
           /* String path = data.getPath();*/
            String toread = settingsload.loadfromtxtfile(uri);
            settingsload.saveReadingCopyTextString(toread);
            settingsload.saveReadingCopyTextboolean(true);


            Intent i = new Intent(this, FullscreenActivity.class);
            finish();
            startActivity(i);



          //  TextView textView = (TextView)findViewById(R.id.textView);
          //  textView.setText(uri);
            // now you call whatever function your app uses
            // to consume the txt file whose location you now know
        } else {
            Log.d(TAG, "intent was something else: "+action);
        }
    }
}