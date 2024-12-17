package fr.opc.practice.p9a11y

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import fr.opc.practice.p9a11y.databinding.ActivityCase3Binding

class Case3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCase3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCase3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Gérer les changements dans le champ de texte
        binding.pseudoEdit.doOnTextChanged { text, _, _, _ ->
            text?.length?.let { textLength ->
                // Validation de la saisie : minimum 3 caractères
                val isValid = textLength > 2
                binding.validateButton.isEnabled = isValid
                binding.pseudoEdit.backgroundTintList = if (isValid) {
                    ColorStateList.valueOf(resources.getColor(R.color.green400, theme))
                } else {
                    ColorStateList.valueOf(resources.getColor(R.color.red400, theme))
                }

                // Affichage ou masquage du message d'erreur avec une suggestion
                if (isValid) {
                    binding.errorMessage.visibility = android.view.View.GONE
                } else {
                    binding.errorMessage.visibility = android.view.View.VISIBLE
                    binding.errorMessage.text = getString(R.string.invalid_input_suggestion)
                }
            }
        }
    }
}
