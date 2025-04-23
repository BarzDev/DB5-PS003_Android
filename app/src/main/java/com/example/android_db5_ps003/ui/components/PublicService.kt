package com.example.android_db5_ps003.ui.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_db5_ps003.R

@Composable
fun PublicServiceComponent(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
        modifier = modifier.fillMaxWidth()
    ) {
        ServiceButton(
            title = "PLN",
            icon = painterResource(id = R.drawable.ic_bolt),
            color = Color.Yellow
        )
        ServiceButton(
            title = "Pajak",
            icon = painterResource(id = R.drawable.ic_bill),
            color = Color.Green
        )
        ServiceButton(
            title = "Kesehatan",
            icon = painterResource(id = R.drawable.ic_medic),
            color = Color.Red
        )
        ServiceButton(
            title = "Hukum",
            icon = painterResource(id = R.drawable.ic_hammer),
            color = Color.Magenta
        )
        ServiceButton(
            title = "Asuransi",
            icon = painterResource(id = R.drawable.ic_shield),
            color = Color.Cyan
        )
    }
}

@Composable
fun ServiceButton(
    title: String,
    icon: Painter,
    color: Color,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val msg = context.getString(R.string.public_service, title)

    Box(
        modifier = modifier
            .clickable {
                Toast
                    .makeText(context, msg, Toast.LENGTH_SHORT)
                    .show()
            }
            .size(64.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color.copy(alpha = 0.1f))
            .padding(8.dp),

        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = icon,
                contentDescription = null,
                colorFilter = ColorFilter.tint(color),
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.sp,
                maxLines = 1,
            )
        }
    }
}

//
//@Preview
//@Composable
//private fun LayoutPreview() {
//    PublicServiceComponent()
//}


