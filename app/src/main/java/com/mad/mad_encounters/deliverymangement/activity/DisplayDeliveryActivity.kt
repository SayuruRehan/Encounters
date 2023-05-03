package com.mad.mad_encounters.deliverymangement.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase
import com.mad.mad_encounters.R
import com.mad.mad_encounters.deliverymangement.model.DeliveryModel

class DisplayDeliveryActivity : AppCompatActivity() {

    private lateinit var tv_display_delivery_Id: TextView
    private lateinit var tv_display_product_name: TextView
    private lateinit var tv_display_address: TextView
    private lateinit var tv_display_delivery_date: TextView
    private lateinit var btn_update1: Button
    private lateinit var btn_delete1: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.display_delivery)

        initView()
        setValuesToViews()

        btn_update1.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra("delivertID").toString(),
                intent.getStringExtra("proName").toString(),
            )
        }

        btn_delete1.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("delivertID").toString()
            )
        }
    }

    private fun deleteRecord(id: String){
        val dbRef = FirebaseDatabase.getInstance().getReference("Deliveries").child(id)
        val dTask = dbRef.removeValue()

        dTask.addOnSuccessListener {
            Toast.makeText(this,"Delivery data deleted", Toast.LENGTH_LONG).show()
            val intent = Intent(this, AllDeliveryOrdersActivity::class.java)
            finish()
            startActivity(intent)
        }
            .addOnFailureListener { err ->
                Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()

            }
    }

    private fun setValuesToViews(){
        tv_display_delivery_Id.text = intent.getStringExtra("delivertID")
        tv_display_product_name.text = intent.getStringExtra("proName")
        tv_display_address.text = intent.getStringExtra("address")
        tv_display_delivery_date.text = intent.getStringExtra("date")
    }

    private fun initView() {
        tv_display_delivery_Id = findViewById(R.id.tv_display_delivery_Id)
        tv_display_product_name = findViewById(R.id.tv_display_product_name)
        tv_display_address = findViewById(R.id.tv_display_address)
        tv_display_delivery_date = findViewById(R.id.tv_display_delivery_date)

        btn_update1 = findViewById(R.id.btn_update1)
        btn_delete1 = findViewById(R.id.btn_delete1)
    }

    private fun openUpdateDialog(delivertID: String, proName: String){
        val dDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dDialogView = inflater.inflate(R.layout.update_delivery,null)

        dDialog.setView(dDialogView)

        val productUpdateName = dDialogView.findViewById<EditText>(R.id.tv_update_product_name)
        val Upaddress = dDialogView.findViewById<EditText>(R.id.tv_update_address)
        val Update = dDialogView.findViewById<EditText>(R.id.tv_update_delivery_date)
        val btnUpdateData = dDialogView.findViewById<Button>(R.id.btn_update)

        productUpdateName.setText(intent.getStringExtra("proName").toString())
        Upaddress.setText(intent.getStringExtra("address").toString())
        Update.setText(intent.getStringExtra("date").toString())

//        dDialog.setTitle("Updating $proName Record")

        val alertDialog = dDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener{
            updateDeliveryData(
                delivertID,
                productUpdateName.text.toString(),
                Upaddress.text.toString(),
                Update.text.toString()
            )
            Toast.makeText(applicationContext, "Delivery data Updated", Toast.LENGTH_LONG).show()

            tv_display_product_name.text = productUpdateName.text.toString()
            tv_display_address.text = Upaddress.text.toString()
            tv_display_delivery_date.text = Update.text.toString()

            alertDialog.dismiss()
        }
    }

    private fun updateDeliveryData(id:String, name:String, address:String, date:String){
        val dbRef = FirebaseDatabase.getInstance().getReference("Deliveries").child(id)
        val deliveryInfo = DeliveryModel(id, name, address, date)
        dbRef.setValue((deliveryInfo))
    }

    fun navigateback(view: View) {
        val intent = Intent(this, AllDeliveryOrdersActivity::class.java)
        startActivity(intent)
    }
}