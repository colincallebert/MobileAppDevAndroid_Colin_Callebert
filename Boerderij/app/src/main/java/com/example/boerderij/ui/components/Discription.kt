import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.boerderij.R


@Composable
fun Discription() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {
        Text(
            text = "Welkom op onze Ouderenboerderij!",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF000000),
            modifier = Modifier
                .padding(top = 40.dp)

        )
        Card(
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp, start = 8.dp, end = 8.dp)
                .border(2.dp, Color.Gray, MaterialTheme.shapes.medium)

        ) {
            Text(
                text = stringResource(id = R.string.overons),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
@Preview
fun DiscriptionPreview() {
    Discription()
}

