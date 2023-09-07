package `as`.example.dobcalculator.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var tvInput : TextView
    private var lastNumeric : Boolean = false
    private var lastDot : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit (view : View ) {
        tvInput.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear (view: View ) {
        tvInput.text = ""
    }

    fun onDecimalPoint (view: View) {
        if (lastNumeric && !lastDot) {
            tvInput.append(".")
            lastDot = true
            lastNumeric = false
        }
    }

    fun onOperator (view: View){
        tvInput?.text?.let {
            if(lastNumeric && !isOperatorAdded(it.toString())){
                tvInput.append( (view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    fun onEqual (view: View) {
        if (lastNumeric){
            var tvValue = tvInput.text.toString()

            var prifix = ""

            if(tvValue.startsWith("-")){
                tvValue = tvValue.substring(1)
                prifix = "-"
            }

//            if (tvValue.contains("-")){
//                try {
//                    val splitValue = tvValue.split("-")
//
//                    var one = splitValue[0]
//                    var two = splitValue[1]
//
//                    if(prifix.isNotEmpty()){
//                        one  = prifix + one
//                    }
//
//                    tvInput.text = (one.toDouble() - two.toDouble()).toString()
//
//                }catch (errer : ArithmeticException){
//                    errer.printStackTrace()
//                }
//            }
            // for Substraction
            try {
                if(tvValue.contains("-")){
                    //suptraction
                    val splitValue = tvValue.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prifix.isNotEmpty()){
                       one = prifix + one
                    }
                    tvInput.text =removeZeroAfterDot( (one.toDouble() - two.toDouble()).toString() )
                }else if(tvValue.contains("+")){
                    //addition
                    val splitValue = tvValue.split("+")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prifix.isNotEmpty()){
                        one = prifix + one
                    }
                    tvInput.text = removeZeroAfterDot( (one.toDouble() + two.toDouble()).toString()  )
                }else if(tvValue.contains("*")){
                    // multiplication
                    val splitValue = tvValue.split("*")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prifix.isNotEmpty()){
                        one = prifix + one
                    }
                    tvInput.text =removeZeroAfterDot(  (one.toDouble() * two.toDouble()).toString() )
                }else if(tvValue.contains("/")){
                    //devide
                    val splitValue = tvValue.split("/")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prifix.isNotEmpty()){
                        one = prifix + one
                    }
                    tvInput.text = removeZeroAfterDot( (one.toDouble() / two.toDouble()).toString() )
                }

            }catch (error : ArithmeticException) {
                error.printStackTrace()
            }




        }

    }

    fun removeZeroAfterDot (result : String) : String {
        var value = result
        if(result.contains(".0")){
            value = value.substring(0, value.length-2)
        }
        return value
    }

    fun isOperatorAdded (value : String) : Boolean {
        return  if(value.startsWith( "-")){
            false
        }else{
            value.contains("+")
                    || value.contains("*")
                    || value.contains("-")
                    || value.contains("/")
        }

    }
}