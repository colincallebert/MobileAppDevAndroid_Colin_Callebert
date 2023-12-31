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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.boerderij.R

/**
 * Composable function representing a UI component with a welcome message and a description card.
 */
@Composable
fun Discription() {
    // Column layout containing welcome message, description card, and spacer
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Welcome message
        Text(
            text = "Welkom op onze Ouderenboerderij!",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF000000), // Black color
            modifier = Modifier.padding(top = 16.dp)
        )

        // Card containing description
        Card(
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp, start = 8.dp, end = 8.dp)
                .border(2.dp, Color.Gray, MaterialTheme.shapes.medium)
        ) {
            Text(
                text = stringResource(id = R.string.overons), // Load description from resources
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }

        // Spacer to push content to the bottom
        Spacer(modifier = Modifier.weight(1f))
    }
}
