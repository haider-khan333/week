package com.android.weeknumber

import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.weeknumber.ui.screen.weeknumber.WeekNumberComposable
import com.android.weeknumber.ui.theme.WeekNumberTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CALENDAR)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(android.Manifest.permission.READ_CALENDAR),
                101
            )
        }

        setContent {
            val context = LocalContext.current
            checkPermissions(context = context)
            WeekNumberComposable()
        }
    }

    private fun checkPermissions(context: Context) {
        Log.d("TAG", "checkPermissions: In check Permission")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            Log.d("TAG", "checkPermissions: Build version grater than S")
            val alarmManager = context.getSystemService(AlarmManager::class.java)
            if (!alarmManager.canScheduleExactAlarms()) {
                requestExactAlarmPermission(context)
                return // Exit function until permission is granted
            } else {
                Log.d("TAG", "checkPermissions: alarm manager can schedule exact alarms")
            }
        } else {
            Log.d("TAG", "checkPermissions: Build version less than S")
        }
    }

    private fun requestExactAlarmPermission(context: Context) {
        Log.d("TAG", "requestExactAlarmPermission: Requesting exact alarm permissions")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
                data = android.net.Uri.parse("package:${context.packageName}")
            }
            context.startActivity(intent)
            Toast.makeText(context, "Grant Exact Alarm Permission in Settings", Toast.LENGTH_LONG)
                .show()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeekNumberTheme {
        Greeting("Android")
    }
}