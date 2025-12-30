package com.example.lab4

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var tvMeal: TextView
    private var mainMeal: String = "無"
    private var sideDish: String = "無"
    private var drink: String = "無"

    // 建立一個 ActivityResultLauncher 來接收從 SelectMealActivity 回傳的結果
    private val selectMealLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // 當 SelectMealActivity 關閉後，這段程式碼會被執行
        if (result.resultCode == Activity.RESULT_OK) {
            // 檢查是否有回傳資料
            val data: Intent? = result.data
            // 從 Intent 中取出名為 "meal_name" 的字串
            val mealName = data?.getStringExtra("meal_name")
            if (mealName != null) {
                mainMeal = mealName // 更新主餐變數
                updateMealText()   // 更新畫面上的文字
            }
        }
    }

    private val selectSideDishLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            // 注意：我們從 "side_dish_name" 這個 key 取得資料
            val sideDishName = data?.getStringExtra("side_dish_name")
            if (sideDishName != null) {
                sideDish = sideDishName // 更新 sideDish 變數
                updateMealText()       // 更新 UI
            }
        }
    }

    private val selectDrinkLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val drinkName = data?.getStringExtra("drink_name")
            if (drinkName != null) {
                drink = drinkName
                updateMealText()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvMeal = findViewById(R.id.tvMeal)
        val btnChooseMainMeal = findViewById<Button>(R.id.btnChoiceMainMeal)

        val btnChooseSideDish = findViewById<Button>(R.id.btnChoiceSideDish)

        val btnChooseDrink = findViewById<Button>(R.id.btnChoiceDrink)

        val btnOrder = findViewById<Button>(R.id.btnOrder)

        // 設定點擊事件
        btnChooseMainMeal.setOnClickListener {
            // 建立一個 Intent 來啟動 SelectMealActivity
            val intent = Intent(this, MainMealActivity::class.java)
            // 使用我們剛剛建立的 launcher 來啟動 Activity
            selectMealLauncher.launch(intent)
        }

        btnChooseSideDish.setOnClickListener {
            val intent = Intent(this, SideDishesActivity::class.java)
            // 使用我們為附餐建立的新 launcher 來啟動
            selectSideDishLauncher.launch(intent)
        }

        btnChooseDrink.setOnClickListener {
            val intent = Intent(this, DrinkActivity::class.java)
            selectDrinkLauncher.launch(intent)
        }

        btnOrder.setOnClickListener {
            // 建立一個 Intent 來啟動 ConfirmActivity
            val intent = Intent(this, ConfirmActivity::class.java)

            // 使用 putExtra 將選擇的餐點資訊放入 Intent 中
            intent.putExtra("main_meal", mainMeal)
            intent.putExtra("side_dish", sideDish)
            intent.putExtra("drink", drink)

            // 啟動 ConfirmActivity
            startActivity(intent)
        }
        // 初始化文字
        updateMealText()
    }

    // 一個輔助函式，用來更新 TextView 的內容
    private fun updateMealText() {
        tvMeal.text = "主餐: $mainMeal\n\n附餐: $sideDish\n\n飲料: $drink"
    }
}