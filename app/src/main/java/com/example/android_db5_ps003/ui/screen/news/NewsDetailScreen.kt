package com.example.android_db5_ps003.ui.screen.news

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.android_db5_ps003.di.Injection
import com.example.android_db5_ps003.ui.viewmodelfactory.NewsViewModelFactory
import com.example.android_db5_ps003.ui.common.UiState
import com.example.android_db5_ps003.ui.theme.Android_DB5PS003Theme

@Composable
fun NewsDetailScreen(
    modifier: Modifier = Modifier,
    id: Int,
    viewModel: NewsDetailViewModel = viewModel(
        factory = NewsViewModelFactory(Injection.provideNewsRepository(LocalContext.current))
    )
) {
    val uiState by viewModel.uiState.collectAsState(initial = UiState.Loading)
    LaunchedEffect(Unit) {
        if (uiState is UiState.Loading) {
            viewModel.getNewsById(id)
        }
    }

    when (uiState) {
        is UiState.Error -> {}
        is UiState.Loading -> {}
        is UiState.Success -> {
            val data = (uiState as UiState.Success).data
            NewsDetailContent(
                urlToImage = data[0].urlToImage.toString(),
                headlineText = data[0].title.toString(),
                date = data[0].publishedAt.toString(),
                content = data[0].content.toString(),
                modifier = modifier
            )
        }
    }
}

@Composable
fun NewsDetailContent(
    modifier: Modifier = Modifier,
    urlToImage: String,
    headlineText: String,
    date: String,
    content: String,
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(urlToImage)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = "Gambar Berita",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(185.dp)
            )
            Text(
                text = date,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = headlineText,
                fontWeight = FontWeight.SemiBold,
                fontSize = 17.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = content,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewsDetailContentPreview() {
    Android_DB5PS003Theme {
        NewsDetailContent(
            urlToImage = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgK8X-TDptGdkQHjhh_EX6-mOfThQbLytuxWL97i2m6FjhFQUECCcN1JVukCK1hkvGkK9wl7R0WjClefYSavc93tmUsGT2KVri1gqy0paLE7PECqCOOs_8GU2bxBdzicBL9wCaxInafYv9X7N5RKXx1lFjgsvYbfXT6eGStCXABcTS2PcsyZpcRnAhluG9U/s4000/20250307_090515.jpg",
            headlineText = "Universitas Putra Bangsa Kembangkan Teknologi Smart Environmental Control (SEC) untuk Budidaya Melon",
            date = "26 Maret 2025",
            content = "KEBUMEN, beritakebumen.co.id - Turnamen Futsal Fort Ramadhan antar desa se-Kabupaten Kebumen tahun 2025 kembali menyajikan laga sengit di partai final yang berlangsung pada Jum'at malam, 28 Maret 2025. Kelurahan Selang sukses mempertahankan gelar juara setelah menaklukkan Desa Seliling dengan skor tipis 2-1 dalam pertandingan yang berlangsung penuh tensi di GOR SMK TKM Pertambangan Kebumen.\n\nKemenangan ini mengukuhkan Kelurahan Selang sebagai juara bertahan atau back-to-back champions setelah sebelumnya juga meraih gelar pada edisi turnamen tahun 2024.\n\nHadir secara langsung dalam laga puncak ini, Ketua Asosiasi Futsal Kabupaten (AFK) Kebumen sekaligus Owner Fort Apparel, M. Wahyu Setiawan, ST, yang menyerahkan langsung trofi kepada tim juara. Dalam pernyataannya, Wahyu menyampaikan bahwa Turnamen Futsal Fort Ramadhan telah menjadi agenda tahunan yang diharapkan dapat terus berkembang dan menjadi ajang silaturahmi dan pembinaan talenta futsal di Kebumen.\n\n“Turnamen ini bukan sekadar kompetisi, tetapi juga menjadi ajang silaturahmi dan pembinaan bagi para pemuda Desa di Kebumen. Kami dari AFK Kebumen bersama Fort Apparel berkomitmen untuk terus mendukung kegiatan ini agar semakin berkualitas dan diminati oleh masyarakat,” ujar Wahyu Setiawan.\n\nSementara itu, Manager tim Kelurahan Selang, Bripka Surya Adi Cahyana, SH., MM., menyampaikan rasa bangganya terhadap para pemain yang telah berjuang hingga meraih kemenangan.\\n\\n“Saya sangat bangga dengan perjuangan para pemain yang tidak kenal lelah hingga berhasil mempertahankan gelar juara. Ini membuktikan bahwa kerja keras, kekompakan dan sabar adalah kunci utama dalam sebuah tim,juga para supporter yang begitu antusisas mendukung tim” ungkapnya.\nHal senada juga diungkapkan oleh Kepala Kelurahan Selang, Yonatan Adam, S.STP., yang menyampaikan apresiasi tinggi terhadap tim dan suporternya nya yang telah mengharumkan nama Kelurahan Selang dalam ajang bergengsi ini.\n\n“Prestasi ini patut dibanggakan, dan semoga ke depannya Kelurahan Selang terus melahirkan atlet-atlet futsal berbakat yang bisa bersaing di level yang lebih tinggi,” ujar Yonatan Adam.\n\nTurnamen Futsal Fort Ramadhan 2025 yang berlangsung setiap malam selama bulan suci Ramadhan ini menjadi salah satu ajang olahraga paling dinanti di Kebumen. Selain menjadi wadah kompetisi, turnamen ini juga menjadi ajang mempererat persaudaraan antar desa dalam semangat sportivitas yang tinggi. Kegiatan ini juga bertujuan untuk mengisi bulan Ramadhan dengan aktivitas yang positif, sekaligus memberikan kesempatan bagi para pemuda untuk menunjukkan bakat mereka di bidang futsal.\n\nDari turnamen ini, Juara 1 diaraih oleh Kelurahan Selang, Juara 2 Desa Seliling Alian, Juara 3 Desa Tersobo Prembun dan Juara 4 Desa Bandung Alian, untuk gelar individu terpilih Ermas Panca Mukti dari Kelurahan Selang sebagai Best Player, Top Scorer berhasil diraih oleh Alfian Setiawan, yang juga berasal dari tim Kelurahan Selang, setelah mencetak 12 gol terbanyak selama turnamen berlangsung dan best goal keeper Panji Numus dari Desa tersobo. (BK/DR)"
        )
    }
}