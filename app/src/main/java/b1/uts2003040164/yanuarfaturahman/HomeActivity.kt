package b1.uts2003040164.yanuarfaturahman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class HomeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var beratBadan: EditText
    private lateinit var tinggiBadan: EditText
    private lateinit var btnHitung: Button
    private lateinit var tvHasil: TextView
    private lateinit var tvHasilKedua: TextView
    private lateinit var spinJenisKelamin: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()

        btnHitung = findViewById(R.id.btnHitung)
        tvHasil = findViewById(R.id.tvHasil)
        tvHasilKedua = findViewById(R.id.tvHasilKedua)

        val spinner = findViewById<Spinner>(R.id.jeniskelamin)

        ArrayAdapter.createFromResource(this, R.array.jenis_kelamin, android.R.layout.simple_spinner_item).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                spinJenisKelamin = p0?.getItemAtPosition(p2).toString()
            }
        }

        btnHitung.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        beratBadan = findViewById(R.id.beratBadan)
        tinggiBadan = findViewById(R.id.tinggiBadan)

        if(p0?.id == R.id.btnHitung) {
            if(beratBadan.text.toString() == "" || tinggiBadan.text.toString() == "") {
                Toast.makeText(applicationContext, "Form wajib diisi!",Toast.LENGTH_LONG).show()
            } else {
                val inputBeratBadan = beratBadan.text.toString().toFloat()
                val inputTinggiBadan = tinggiBadan.text.toString().toFloat()
                val tinggiMeter = inputTinggiBadan / 100
                val bmi = inputBeratBadan / (tinggiMeter * tinggiMeter)

                tvHasil.text = "BMI kamu adalah ${bmi}"

                if(spinJenisKelamin == "Laki-laki") {
                    if (bmi < 17) {
                        tvHasilKedua.text = "Kamu masuk kategori KURUS"
                    } else if (bmi in 17.0..23.0) {
                        tvHasilKedua.text = "Kamu masuk kategori NORMAL"
                    } else if (bmi in 23.0..27.0) {
                        tvHasilKedua.text = "Kamu masuk kategori KEGEMUKAN"
                    } else {
                        tvHasilKedua.text = "Kamu masuk kategori OBESITAS"
                    }
                } else {
                    if (bmi < 18) {
                        tvHasilKedua.text = "Kamu masuk kategori KURUS"
                    } else if (bmi in 18.0..25.0) {
                        tvHasilKedua.text = "Kamu masuk kategori NORMAL"
                    } else if (bmi in 25.0..27.0) {
                        tvHasilKedua.text = "Kamu masuk kategori KEGEMUKAN"
                    } else {
                        tvHasilKedua.text = "Kamu masuk kategori OBESITAS"
                    }
                }
            }
        }
    }
}
