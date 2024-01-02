import com.example.boerderij.data.FakeApiAppRepository
import com.example.boerderij.data.FakeDataSource.registrations
import com.example.boerderij.model.registration.Registration
import com.example.boerderij.network.activityApi.ActivitiesApiState
import com.example.boerderij.ui.TestDispatchersRule
import com.example.boerderij.viewmodel.ActivityViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ActivityModelTest {

    private lateinit var viewModel: ActivityViewModel

    @get:Rule
    val testDispatcherRule = TestDispatchersRule()

    @Before
    fun setup() {
        viewModel = ActivityViewModel(FakeApiAppRepository())
    }
    @Test
    fun getActivities_methodCall_StateIsSuccessAfterCall() {
        val expectedActivityApiState = ActivitiesApiState.Success

        viewModel.getActivities()

        Assert.assertEquals(expectedActivityApiState, viewModel.activityApiState)
    }
    @Test
    fun createRegistration_methodCall_StateIsSuccessAfterCall() {
        val expectedActivityApiState = ActivitiesApiState.Success

        viewModel.registreren(3, 1)

        Assert.assertEquals(expectedActivityApiState, viewModel.activityApiState)
    }

    @Test
    fun deleteRegistration_methodCall_StateIsSuccessAfterCall() {
        val expectedActivityApiState = ActivitiesApiState.Success

        viewModel.deleteRegistration(2)

        Assert.assertEquals(expectedActivityApiState, viewModel.activityApiState)
    }


}
