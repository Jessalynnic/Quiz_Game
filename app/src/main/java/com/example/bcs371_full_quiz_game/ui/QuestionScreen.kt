package com.example.bcs371_full_quiz_game.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bcs371_full_quiz_game.ui.data.QuestionType
import com.example.bcs371_full_quiz_game.ui.data.QuizViewModel

@Composable
fun QuestionScreen(navController: NavController, viewModel: QuizViewModel) {
    // Get the current context for displaying Toasts
    val  context = LocalContext.current
    // Get the current question from ViewModel
    val currentQuestion = viewModel.getCurrentQuestion()

    Column(
        modifier = Modifier
            .fillMaxSize()
            // Enable vertical scrolling
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        // Top bar with the title of the quiz
        Box(
            modifier = Modifier
                .statusBarsPadding() // Padding to avoid status bar overlap
                .fillMaxWidth()
                .height(100.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Are You Smarter Than A",
                    fontSize = 30.sp,
                    color = Color(0xFFF06292),
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
                )
                Text(
                    text = "Fifth Grader?",
                    fontSize = 30.sp,
                    color = Color(0xFFF06292)
                )
            }
        }
        // Box displaying the amount earned
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                // Display the earned amount
                text = "You Earned : $" + viewModel.earnedAmount,
                fontSize = 30.sp,
                color = Color(0xFF4FC3F7)
            )
        }
        // Column for the question and answer options
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            // Display the current question text
            Text(
                text = currentQuestion.questionText,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center// Center the text within the Text composable
            )


            // Display different UI based on the question type
            when (currentQuestion.questionType) {
                QuestionType.RADIO -> {
                    currentQuestion.options.forEach { option ->
                        // Check if the current option is selected, apply a green background color
                        val isSelected = viewModel.selectedAnswer == option
                        val backgroundColor = if (isSelected) Color.Green else Color.Transparent

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .width(150.dp)
                                .background(backgroundColor) // Highlight selected option
                                .clickable { viewModel.selectAnswer(option) }
                                .padding(8.dp)
                        ) {
                            RadioButton(
                                // Show the radio button as selected if it matches
                                selected = viewModel.selectedAnswer == option,
                                // Update selected answer
                                onClick = { viewModel.selectAnswer(option) }
                            )
                            Text(
                                text = option, // Display the option text
                                fontSize = 17.sp,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }

                QuestionType.CHECKBOX -> {
                    // Add spacing above the options
                    Spacer(modifier = Modifier.height(25.dp))

                    currentQuestion.options.forEach { option ->
                        // Check if the option is selected
                        val isSelected = viewModel.selectedCheckboxes.contains(option)
                        // Highlight selected option
                        val backgroundColor = if (isSelected) Color.Green else Color.Transparent

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .width(150.dp)
                                .background(backgroundColor)
                                .padding(vertical = 8.dp)
                        ) {
                            Checkbox(
                                checked = viewModel.selectedCheckboxes.contains(option),
                                onCheckedChange = {
                                    viewModel.toggleCheckbox(option) // Toggle checkbox selection
                                }
                            )
                            Text(
                                text = option, // Display the option text
                                fontSize = 17.sp,
                                modifier = Modifier.padding(start = 5.dp)
                            )
                        }
                    }
                }

                QuestionType.DROPDOWN -> {
                    // Track if the dropdown is expanded
                    var expanded by remember { mutableStateOf(false) }
                    // Track the selected option
                    var selectedOption by remember { mutableStateOf("") }

                    Box(
                        modifier = Modifier.padding(top = 20.dp)
                    ) {
                        OutlinedButton(onClick = { expanded = !expanded }) {
                            // Show the selected option or default text
                            Text(selectedOption.ifEmpty { "Select an option" }, fontSize = 15.sp)
                        }
                        DropdownMenu(
                            expanded = expanded,
                            // Close dropdown on outside click
                            onDismissRequest = { expanded = false }
                        ) {
                            currentQuestion.options.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(text = option, fontSize = 17.sp) },
                                    onClick = {
                                        selectedOption = option // Update the selected option
                                        expanded = false // Close the dropdown
                                        viewModel.selectAnswer(option)  // Update the selected answer in ViewModel
                                    }
                                )
                            }
                        }
                    }
                }

                QuestionType.SLIDER -> {
                    currentQuestion.sliderRange?.let { range ->
                        // Add spacing before the slider
                        Spacer(modifier = Modifier.padding(20.dp))
                        // Show the current slider value
                        Text(text = "Selected value: ${viewModel.selectedAnswer}", fontSize = 17.sp)

                        Slider(
                            // Set slider value
                            value = viewModel.selectedAnswer.toFloatOrNull() ?: range.start,
                            // Update answer on value change
                            onValueChange = { viewModel.selectAnswer(it.toInt().toString()) },
                            // Define the slider range
                            valueRange = range,
                            // Set step count based on range
                            steps = (range.endInclusive - range.start).toInt() - 1
                        )
                    }
                }

                QuestionType.TOGGLE -> {
                    // Track toggle state
                    var toggleState by remember { mutableStateOf(false) }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            // Toggle the switch state
                            .clickable { toggleState = !toggleState }
                            .padding(top = 20.dp)
                    ) {
                        Switch(
                            // Show switch as checked if toggleState is true
                            checked = toggleState,
                            onCheckedChange = {
                                toggleState = it
                                viewModel.selectAnswer(if (it) "True" else "False")  // Update the selected answer
                            }
                        )
                        Text(
                            // Show "True" or "False" based on state
                            text = if (toggleState) "True" else "False",
                            fontSize = 17.sp,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
            }
        }

        // Spacer to push the button to the bottom
        Spacer(modifier = Modifier.weight(.5f))

        // Confirm Button to submit the selected answer
        Button(
            onClick = {
                // Check if the answer is correct and display a corresponding toast
                when (currentQuestion.questionType) {
                    QuestionType.RADIO, QuestionType.DROPDOWN, QuestionType.TOGGLE -> {
                        if (viewModel.selectedAnswer == currentQuestion.correctAnswer.first()) {
                            // Add earnings for a correct answer
                            viewModel.addEarnings(100)
                            // Increment correct answers count
                            viewModel.incrementCorrectAnswers()
                            Toast.makeText(
                                context,
                                "This is the CORRECT answer. You earned $" + viewModel.earnedAmount,
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                context,
                                "This is NOT the correct answer.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    // Check if the answer is correct and display a corresponding toast
                    QuestionType.CHECKBOX -> {
                        val selectedAnswerSet = viewModel.selectedCheckboxes.toSet()
                        if (selectedAnswerSet == currentQuestion.correctAnswer.toSet()) {
                            // Add earnings for a correct answer
                            viewModel.addEarnings(100)
                            // Increment correct answers count
                            viewModel.incrementCorrectAnswers()
                            Toast.makeText(
                                context,
                                "This is the CORRECT answer. You earned $" + viewModel.earnedAmount,
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                context,
                                "This is NOT the correct answer.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    // Check if the answer is correct and display a corresponding toast
                    QuestionType.SLIDER -> {
                        if (viewModel.selectedAnswer == currentQuestion.correctAnswer.first()) {
                            // Add earnings for a correct answer
                            viewModel.addEarnings(100)
                            // Increment correct answers count
                            viewModel.incrementCorrectAnswers()
                            Toast.makeText(
                                context,
                                "That is the CORRECT answer. You earned $" + viewModel.earnedAmount,
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                context,
                                "That is NOT the correct answer.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
                // Navigate to the StatScreen if it's the last question, otherwise move to the next question
                if (viewModel.isLastQuestion()) {
                    navController.navigate("stat_screen")
                } else {
                    // Move to the next question
                    viewModel.moveToNextQuestion()
                }
            },
            modifier = Modifier
                .width(200.dp)
                .padding(bottom = 130.dp)
        ) {
            // Button label
            Text(
                text = "Confirm",
                fontSize = 15.sp,
            )
        }
    }
}