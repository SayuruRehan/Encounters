package com.mad.mad_encounters.inventorymanagement.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase
import com.mad.mad_encounters.R
import com.mad.mad_encounters.inventorymanagement.model.InventoryItem

class InventoryDetailsActivity : AppCompatActivity() {


    private lateinit var tvCusId: TextView
    private lateinit var tvCusName: TextView
    private lateinit var tvItem: TextView
    private lateinit var tvQuantity: TextView
    private lateinit var tvCusCountry: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inventry_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("cusId").toString(),
            )
        }

        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("cusId").toString()
            )
        }

        val backButton = findViewById<ImageView>(R.id.backBtn)
        backButton.setOnClickListener {
            finish()
        }
    }



    private fun initView() {
        tvCusId = findViewById(R.id.cusId)
        tvCusName = findViewById(R.id.cusName)
        tvItem = findViewById(R.id.item)
        tvQuantity = findViewById(R.id.quantity)
        tvCusCountry = findViewById(R.id.cusCountry)


        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        tvCusId.text = intent.getStringExtra("cusId")
        tvCusName.text = intent.getStringExtra("cusName")
        tvItem.text = intent.getStringExtra("item")
        tvQuantity.text = intent.getStringExtra("quantity")
        tvCusCountry.text = intent.getStringExtra("cusCountry")

    }

    private fun openUpdateDialog(cusId:String){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.activity_update_inventory, null)

        mDialog.setView(mDialogView)

        val etCusName = mDialogView.findViewById<EditText>(R.id.etCusName)
        val etCusCountry = mDialogView.findViewById<EditText>(R.id.etCusCountry)
        val etItem = mDialogView.findViewById<EditText>(R.id.etItem)
        val etQuantity = mDialogView.findViewById<EditText>(R.id.etQuantity)
        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etCusName.setText(intent.getStringExtra("cusName").toString())
        etCusCountry.setText(intent.getStringExtra("cusCountry").toString())
        etQuantity.setText(intent.getStringExtra("quantity").toString())
        etItem.setText(intent.getStringExtra("item").toString())

        mDialog.setTitle("Updating $cusId Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener{
            updateInvData(
                cusId,
                etCusName.text.toString(),
                etItem.text.toString(),
                etQuantity.text.toString(),
                etCusCountry.text.toString(),
            )
            Toast.makeText(applicationContext, "Employee Data Updated", Toast.LENGTH_LONG).show()

//            tvCusId.text = etCusId.text.toString()
            tvCusName.text = etCusName.text.toString()
            tvCusCountry.text = etCusCountry.text.toString()
            tvItem.text = etItem.text.toString()
            tvQuantity.text = etQuantity.text.toString()

            alertDialog.dismiss()
        }
    }
    private fun updateInvData(cusId: String, cusName: String, item:String, quantity: String, cusCountry: String,  ){

        val dbRef = FirebaseDatabase.getInstance().getReference("InventoryItems").child(cusId)
        val itemDetails = InventoryItem(cusId, cusName,item, quantity, cusCountry )
        dbRef.setValue(itemDetails)

    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("InventoryItems").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Employee data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, ViewInventory::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }
}