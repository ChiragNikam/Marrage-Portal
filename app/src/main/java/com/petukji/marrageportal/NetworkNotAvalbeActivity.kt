package com.petukji.marrageportal

import android.content.Context
import android.content.Intent
import android.graphics.fonts.FontStyle
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.petukji.marrageportal.bottom_nav.presentation.HomeActivity
import com.petukji.marrageportal.ui.theme.MarriagePortalTheme

class NetworkNotAvalbeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            MarriagePortalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        Column(
                            modifier = Modifier
                        ) {
                            Image(modifier = Modifier.padding(horizontal = 100.dp), painter = painterResource(id = R.drawable.nointernet), contentDescription ="No connection" )

                            Spacer(modifier = Modifier.height(20.dp))

                            Text(modifier = Modifier
                                .padding(horizontal = 8.dp),
                                text = "Network Issue,\nPlease check Your Internet Connectivity"
                                , fontSize = 16.sp,)
                            Spacer(modifier = Modifier.height(40.dp))

                            Button(modifier = Modifier.padding(horizontal = 100.dp)
                                , onClick = {
                                    if (isNetworkAvailable()) {
                                        startActivity(Intent(this@NetworkNotAvalbeActivity,HomeActivity::class.java))
                                        finish()
                                    }
                                }) {
                                Text(text = "Refresh Page")
                            }
                        }
                    }
                }
            }
        }

    }
    fun isNetworkAvailable(): Boolean {
        val connectivityManager=getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        return networkInfo!=null && networkInfo.isConnected
    }
}


