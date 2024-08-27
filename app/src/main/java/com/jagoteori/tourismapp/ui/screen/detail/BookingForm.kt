package com.jagoteori.tourismapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jagoteori.tourismapp.ui.screen.detail.BookingFormState
//import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
@Composable
fun BookingForm(
    bookingFormState: BookingFormState,
    onFormChange: (BookingFormState) -> Unit,
    onSubmit: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Reserva") },
        text = {
            Column(
                modifier = modifier.padding(16.dp)
            ) {
                OutlinedTextField(
                    value = bookingFormState.name,
                    onValueChange = { newName -> onFormChange(bookingFormState.copy(name = newName)) },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = bookingFormState.email,
                    onValueChange = { newEmail -> onFormChange(bookingFormState.copy(email = newEmail)) },
                    label = { Text("Correo Electrónico") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = bookingFormState.departureDate,
                    onValueChange = { newDate -> onFormChange(bookingFormState.copy(departureDate = newDate)) },
                    label = { Text("Fecha de Salida") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = bookingFormState.numAdults.toString(),
                    onValueChange = { newNumAdults -> onFormChange(bookingFormState.copy(numAdults = newNumAdults.toIntOrNull() ?: 1)) },
                    label = { Text("Número de Adultos") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = bookingFormState.ageGroup,
                    onValueChange = { newAgeGroup -> onFormChange(bookingFormState.copy(ageGroup = newAgeGroup)) },
                    label = { Text("Grupo de Edad") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = bookingFormState.tripType,
                    onValueChange = { newTripType -> onFormChange(bookingFormState.copy(tripType = newTripType)) },
                    label = { Text("Tipo de Viaje") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = bookingFormState.selectedActivities.joinToString(", "),
                    onValueChange = { newActivities -> onFormChange(bookingFormState.copy(selectedActivities = newActivities.split(", ").map { it.trim() })) },
                    label = { Text("Actividades Seleccionadas") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = bookingFormState.accommodationType,
                    onValueChange = { newAccommodationType -> onFormChange(bookingFormState.copy(accommodationType = newAccommodationType)) },
                    label = { Text("Tipo de Alojamiento") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = bookingFormState.additionalServices.joinToString(", "),
                    onValueChange = { newServices -> onFormChange(bookingFormState.copy(additionalServices = newServices.split(", ").map { it.trim() })) },
                    label = { Text("Servicios Adicionales") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onSubmit
            ) {
                Text("Confirmar")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss
            ) {
                Text("Cancelar")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewBookingForm() {
    var bookingFormState by remember { mutableStateOf(BookingFormState()) }

    BookingForm(
        bookingFormState = bookingFormState,
        onFormChange = { newState -> bookingFormState = newState },
        onSubmit = { /* Handle submit */ },
        onDismiss = { /* Handle dismiss */ }
    )
}
