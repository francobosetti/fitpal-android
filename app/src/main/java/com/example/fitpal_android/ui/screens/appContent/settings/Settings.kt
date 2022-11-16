package com.example.fitpal_android.ui.screens.appContent.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitpal_android.R
import com.example.fitpal_android.util.getViewModelFactory

@Composable
fun Settings() {
    val viewModel = viewModel<SettingsViewModel>(factory = getViewModelFactory())

    Surface(color = MaterialTheme.colors.background, modifier = Modifier.padding(8.dp)) {

        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = MaterialTheme.colors.onPrimary,
            shape = RoundedCornerShape(12.dp),
        ) {

            Column {

                // Execution mode
                Text(
                    text = stringResource(R.string.execution_mode),
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(8.dp)
                )

                // Toggle switch for execution mode
                Switch(
                    checked = viewModel.settingsState.executionMode, onCheckedChange = {
                        viewModel.toggleExecutionMode()
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colors.primary,
                        checkedTrackColor = MaterialTheme.colors.background,
                        uncheckedThumbColor = MaterialTheme.colors.background,
                        uncheckedTrackColor = MaterialTheme.colors.primary
                    ),
                    modifier = Modifier.padding(start = 8.dp, top = 2.dp, bottom = 2.dp)
                )

                // TODO only for testing
                Text(
                    text = if (viewModel.isDetailedMode()) "Detailed mode" else "Simple mode",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(8.dp)
                )

            }

        }

    }
}