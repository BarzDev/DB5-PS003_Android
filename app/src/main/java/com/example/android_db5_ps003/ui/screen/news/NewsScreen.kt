package com.example.android_db5_ps003.ui.screen.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.android_db5_ps003.data.remote.response.NewsItem
import com.example.android_db5_ps003.ui.components.NewsListItem
import com.example.android_db5_ps003.ui.theme.Android_DB5PS003Theme

@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
    navigateToNewsDetail: () -> Unit
) {

}

@Composable
fun NewsContent(
    modifier: Modifier = Modifier,
    newsList: List<NewsItem>
) {
    LazyColumn {
        items(newsList, key = { it.id }) { data ->
            NewsListItem(
                imageUrl = data.urlToImage.toString(),
                headlineText = data.title.toString(),
                shortDesc = data.description.toString(),
                date = data.publishedAt.toString(),
                modifier = Modifier.clickable {  }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewsContentPreview() {
    Android_DB5PS003Theme {
        NewsContent(
            newsList = listOf(
                NewsItem(
                    publishedAt = "26 Maret 2025",
                    urlToImage = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhZELNL5kuc2uWILtOG6XqCMU1SVxKwLdTz2eTUqXk1FLTbDA5NyzYfcqmW4smMMaFZRRzqVH10lyk5ExRKDVqTLPMdQ-kL38XS9Mo9xOVaq843pRcbvvbR6ftcIFsazJOKCZVfsvr9KbaVc7Sr28nd4dxZNXW63o9FM72cnWoP6C1_4DXt-RFDGhp2F95v/s1600/WhatsApp%20Image%202025-03-29%20at%2016.09.26.jpeg",
                    description = "Turnamen Futsal Fort Ramadhan antar desa se-Kabupaten Kebumen tahun 2025 kembali menyajikan laga sengit di partai final yang berlangsung pada Jum'at malam, 28 Maret 2025. Kelurahan Selang sukses mempertahankan gelar juara setelah menaklukkan Desa Seliling dengan skor tipis 2-1 dalam pertandingan yang berlangsung penuh tensi di GOR SMK TKM Pertambangan Kebumen",
                    id = 1,
                    title = "Kelurahan Selang Back-to-Back Champions di Turnamen Futsal Fort Ramadhan 2025",
                    url = "https://www.beritakebumen.co.id/2025/03/kelurahan-selang-back-to-back-champions.html",
                    content = "KEBUMEN, beritakebumen.co.id - Turnamen Futsal Fort Ramadhan antar desa se-Kabupaten Kebumen tahun 2025 kembali menyajikan laga sengit di partai final yang berlangsung pada Jum'at malam, 28 Maret 2025. Kelurahan Selang sukses mempertahankan gelar juara setelah menaklukkan Desa Seliling dengan skor tipis 2-1 dalam pertandingan yang berlangsung penuh tensi di GOR SMK TKM Pertambangan Kebumen.\\n\\nKemenangan ini mengukuhkan Kelurahan Selang sebagai juara bertahan atau back-to-back champions setelah sebelumnya juga meraih gelar pada edisi turnamen tahun 2024.\\n\\nHadir secara langsung dalam laga puncak ini, Ketua Asosiasi Futsal Kabupaten (AFK) Kebumen sekaligus Owner Fort Apparel, M. Wahyu Setiawan, ST, yang menyerahkan langsung trofi kepada tim juara. Dalam pernyataannya, Wahyu menyampaikan bahwa Turnamen Futsal Fort Ramadhan telah menjadi agenda tahunan yang diharapkan dapat terus berkembang dan menjadi ajang silaturahmi dan pembinaan talenta futsal di Kebumen.\\n\\n“Turnamen ini bukan sekadar kompetisi, tetapi juga menjadi ajang silaturahmi dan pembinaan bagi para pemuda Desa di Kebumen. Kami dari AFK Kebumen bersama Fort Apparel berkomitmen untuk terus mendukung kegiatan ini agar semakin berkualitas dan diminati oleh masyarakat,” ujar Wahyu Setiawan.\\n\\nSementara itu, Manager tim Kelurahan Selang, Bripka Surya Adi Cahyana, SH., MM., menyampaikan rasa bangganya terhadap para pemain yang telah berjuang hingga meraih kemenangan.\\n\\n“Saya sangat bangga dengan perjuangan para pemain yang tidak kenal lelah hingga berhasil mempertahankan gelar juara. Ini membuktikan bahwa kerja keras, kekompakan dan sabar adalah kunci utama dalam sebuah tim,juga para supporter yang begitu antusisas mendukung tim” ungkapnya.\\nHal senada juga diungkapkan oleh Kepala Kelurahan Selang, Yonatan Adam, S.STP., yang menyampaikan apresiasi tinggi terhadap tim dan suporternya nya yang telah mengharumkan nama Kelurahan Selang dalam ajang bergengsi ini.\\n\\n“Prestasi ini patut dibanggakan, dan semoga ke depannya Kelurahan Selang terus melahirkan atlet-atlet futsal berbakat yang bisa bersaing di level yang lebih tinggi,” ujar Yonatan Adam.\\n\\nTurnamen Futsal Fort Ramadhan 2025 yang berlangsung setiap malam selama bulan suci Ramadhan ini menjadi salah satu ajang olahraga paling dinanti di Kebumen. Selain menjadi wadah kompetisi, turnamen ini juga menjadi ajang mempererat persaudaraan antar desa dalam semangat sportivitas yang tinggi. Kegiatan ini juga bertujuan untuk mengisi bulan Ramadhan dengan aktivitas yang positif, sekaligus memberikan kesempatan bagi para pemuda untuk menunjukkan bakat mereka di bidang futsal.\\n\\nDari turnamen ini, Juara 1 diaraih oleh Kelurahan Selang, Juara 2 Desa Seliling Alian, Juara 3 Desa Tersobo Prembun dan Juara 4 Desa Bandung Alian, untuk gelar individu terpilih Ermas Panca Mukti dari Kelurahan Selang sebagai Best Player, Top Scorer berhasil diraih oleh Alfian Setiawan, yang juga berasal dari tim Kelurahan Selang, setelah mencetak 12 gol terbanyak selama turnamen berlangsung dan best goal keeper Panji Numus dari Desa tersobo. (BK/DR)"
                ),NewsItem(
                    publishedAt = "26 Maret 2025",
                    urlToImage = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhZELNL5kuc2uWILtOG6XqCMU1SVxKwLdTz2eTUqXk1FLTbDA5NyzYfcqmW4smMMaFZRRzqVH10lyk5ExRKDVqTLPMdQ-kL38XS9Mo9xOVaq843pRcbvvbR6ftcIFsazJOKCZVfsvr9KbaVc7Sr28nd4dxZNXW63o9FM72cnWoP6C1_4DXt-RFDGhp2F95v/s1600/WhatsApp%20Image%202025-03-29%20at%2016.09.26.jpeg",
                    description = "Turnamen Futsal Fort Ramadhan antar desa se-Kabupaten Kebumen tahun 2025 kembali menyajikan laga sengit di partai final yang berlangsung pada Jum'at malam, 28 Maret 2025. Kelurahan Selang sukses mempertahankan gelar juara setelah menaklukkan Desa Seliling dengan skor tipis 2-1 dalam pertandingan yang berlangsung penuh tensi di GOR SMK TKM Pertambangan Kebumen",
                    id = 2,
                    title = "Kelurahan Selang Back-to-Back Champions di Turnamen Futsal Fort Ramadhan 2025",
                    url = "https://www.beritakebumen.co.id/2025/03/kelurahan-selang-back-to-back-champions.html",
                    content = "KEBUMEN, beritakebumen.co.id - Turnamen Futsal Fort Ramadhan antar desa se-Kabupaten Kebumen tahun 2025 kembali menyajikan laga sengit di partai final yang berlangsung pada Jum'at malam, 28 Maret 2025. Kelurahan Selang sukses mempertahankan gelar juara setelah menaklukkan Desa Seliling dengan skor tipis 2-1 dalam pertandingan yang berlangsung penuh tensi di GOR SMK TKM Pertambangan Kebumen.\\n\\nKemenangan ini mengukuhkan Kelurahan Selang sebagai juara bertahan atau back-to-back champions setelah sebelumnya juga meraih gelar pada edisi turnamen tahun 2024.\\n\\nHadir secara langsung dalam laga puncak ini, Ketua Asosiasi Futsal Kabupaten (AFK) Kebumen sekaligus Owner Fort Apparel, M. Wahyu Setiawan, ST, yang menyerahkan langsung trofi kepada tim juara. Dalam pernyataannya, Wahyu menyampaikan bahwa Turnamen Futsal Fort Ramadhan telah menjadi agenda tahunan yang diharapkan dapat terus berkembang dan menjadi ajang silaturahmi dan pembinaan talenta futsal di Kebumen.\\n\\n“Turnamen ini bukan sekadar kompetisi, tetapi juga menjadi ajang silaturahmi dan pembinaan bagi para pemuda Desa di Kebumen. Kami dari AFK Kebumen bersama Fort Apparel berkomitmen untuk terus mendukung kegiatan ini agar semakin berkualitas dan diminati oleh masyarakat,” ujar Wahyu Setiawan.\\n\\nSementara itu, Manager tim Kelurahan Selang, Bripka Surya Adi Cahyana, SH., MM., menyampaikan rasa bangganya terhadap para pemain yang telah berjuang hingga meraih kemenangan.\\n\\n“Saya sangat bangga dengan perjuangan para pemain yang tidak kenal lelah hingga berhasil mempertahankan gelar juara. Ini membuktikan bahwa kerja keras, kekompakan dan sabar adalah kunci utama dalam sebuah tim,juga para supporter yang begitu antusisas mendukung tim” ungkapnya.\\nHal senada juga diungkapkan oleh Kepala Kelurahan Selang, Yonatan Adam, S.STP., yang menyampaikan apresiasi tinggi terhadap tim dan suporternya nya yang telah mengharumkan nama Kelurahan Selang dalam ajang bergengsi ini.\\n\\n“Prestasi ini patut dibanggakan, dan semoga ke depannya Kelurahan Selang terus melahirkan atlet-atlet futsal berbakat yang bisa bersaing di level yang lebih tinggi,” ujar Yonatan Adam.\\n\\nTurnamen Futsal Fort Ramadhan 2025 yang berlangsung setiap malam selama bulan suci Ramadhan ini menjadi salah satu ajang olahraga paling dinanti di Kebumen. Selain menjadi wadah kompetisi, turnamen ini juga menjadi ajang mempererat persaudaraan antar desa dalam semangat sportivitas yang tinggi. Kegiatan ini juga bertujuan untuk mengisi bulan Ramadhan dengan aktivitas yang positif, sekaligus memberikan kesempatan bagi para pemuda untuk menunjukkan bakat mereka di bidang futsal.\\n\\nDari turnamen ini, Juara 1 diaraih oleh Kelurahan Selang, Juara 2 Desa Seliling Alian, Juara 3 Desa Tersobo Prembun dan Juara 4 Desa Bandung Alian, untuk gelar individu terpilih Ermas Panca Mukti dari Kelurahan Selang sebagai Best Player, Top Scorer berhasil diraih oleh Alfian Setiawan, yang juga berasal dari tim Kelurahan Selang, setelah mencetak 12 gol terbanyak selama turnamen berlangsung dan best goal keeper Panji Numus dari Desa tersobo. (BK/DR)"
                ),NewsItem(
                    publishedAt = "26 Maret 2025",
                    urlToImage = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhZELNL5kuc2uWILtOG6XqCMU1SVxKwLdTz2eTUqXk1FLTbDA5NyzYfcqmW4smMMaFZRRzqVH10lyk5ExRKDVqTLPMdQ-kL38XS9Mo9xOVaq843pRcbvvbR6ftcIFsazJOKCZVfsvr9KbaVc7Sr28nd4dxZNXW63o9FM72cnWoP6C1_4DXt-RFDGhp2F95v/s1600/WhatsApp%20Image%202025-03-29%20at%2016.09.26.jpeg",
                    description = "Turnamen Futsal Fort Ramadhan antar desa se-Kabupaten Kebumen tahun 2025 kembali menyajikan laga sengit di partai final yang berlangsung pada Jum'at malam, 28 Maret 2025. Kelurahan Selang sukses mempertahankan gelar juara setelah menaklukkan Desa Seliling dengan skor tipis 2-1 dalam pertandingan yang berlangsung penuh tensi di GOR SMK TKM Pertambangan Kebumen",
                    id = 3,
                    title = "Kelurahan Selang Back-to-Back Champions di Turnamen Futsal Fort Ramadhan 2025",
                    url = "https://www.beritakebumen.co.id/2025/03/kelurahan-selang-back-to-back-champions.html",
                    content = "KEBUMEN, beritakebumen.co.id - Turnamen Futsal Fort Ramadhan antar desa se-Kabupaten Kebumen tahun 2025 kembali menyajikan laga sengit di partai final yang berlangsung pada Jum'at malam, 28 Maret 2025. Kelurahan Selang sukses mempertahankan gelar juara setelah menaklukkan Desa Seliling dengan skor tipis 2-1 dalam pertandingan yang berlangsung penuh tensi di GOR SMK TKM Pertambangan Kebumen.\\n\\nKemenangan ini mengukuhkan Kelurahan Selang sebagai juara bertahan atau back-to-back champions setelah sebelumnya juga meraih gelar pada edisi turnamen tahun 2024.\\n\\nHadir secara langsung dalam laga puncak ini, Ketua Asosiasi Futsal Kabupaten (AFK) Kebumen sekaligus Owner Fort Apparel, M. Wahyu Setiawan, ST, yang menyerahkan langsung trofi kepada tim juara. Dalam pernyataannya, Wahyu menyampaikan bahwa Turnamen Futsal Fort Ramadhan telah menjadi agenda tahunan yang diharapkan dapat terus berkembang dan menjadi ajang silaturahmi dan pembinaan talenta futsal di Kebumen.\\n\\n“Turnamen ini bukan sekadar kompetisi, tetapi juga menjadi ajang silaturahmi dan pembinaan bagi para pemuda Desa di Kebumen. Kami dari AFK Kebumen bersama Fort Apparel berkomitmen untuk terus mendukung kegiatan ini agar semakin berkualitas dan diminati oleh masyarakat,” ujar Wahyu Setiawan.\\n\\nSementara itu, Manager tim Kelurahan Selang, Bripka Surya Adi Cahyana, SH., MM., menyampaikan rasa bangganya terhadap para pemain yang telah berjuang hingga meraih kemenangan.\\n\\n“Saya sangat bangga dengan perjuangan para pemain yang tidak kenal lelah hingga berhasil mempertahankan gelar juara. Ini membuktikan bahwa kerja keras, kekompakan dan sabar adalah kunci utama dalam sebuah tim,juga para supporter yang begitu antusisas mendukung tim” ungkapnya.\\nHal senada juga diungkapkan oleh Kepala Kelurahan Selang, Yonatan Adam, S.STP., yang menyampaikan apresiasi tinggi terhadap tim dan suporternya nya yang telah mengharumkan nama Kelurahan Selang dalam ajang bergengsi ini.\\n\\n“Prestasi ini patut dibanggakan, dan semoga ke depannya Kelurahan Selang terus melahirkan atlet-atlet futsal berbakat yang bisa bersaing di level yang lebih tinggi,” ujar Yonatan Adam.\\n\\nTurnamen Futsal Fort Ramadhan 2025 yang berlangsung setiap malam selama bulan suci Ramadhan ini menjadi salah satu ajang olahraga paling dinanti di Kebumen. Selain menjadi wadah kompetisi, turnamen ini juga menjadi ajang mempererat persaudaraan antar desa dalam semangat sportivitas yang tinggi. Kegiatan ini juga bertujuan untuk mengisi bulan Ramadhan dengan aktivitas yang positif, sekaligus memberikan kesempatan bagi para pemuda untuk menunjukkan bakat mereka di bidang futsal.\\n\\nDari turnamen ini, Juara 1 diaraih oleh Kelurahan Selang, Juara 2 Desa Seliling Alian, Juara 3 Desa Tersobo Prembun dan Juara 4 Desa Bandung Alian, untuk gelar individu terpilih Ermas Panca Mukti dari Kelurahan Selang sebagai Best Player, Top Scorer berhasil diraih oleh Alfian Setiawan, yang juga berasal dari tim Kelurahan Selang, setelah mencetak 12 gol terbanyak selama turnamen berlangsung dan best goal keeper Panji Numus dari Desa tersobo. (BK/DR)"
                ),
            )
        )
    }
}