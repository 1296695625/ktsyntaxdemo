package  com.example.administrator.epgiskotlin.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.administrator.epgiskotlin.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        if (v.id == R.id.loginbt) {
//            tv_name.setText(""+Math.random())
            var intent1: Intent=Intent();
            intent1.putExtra("name", tv_name.text)
            intent1.setClass(this, HomeActivity::class.java)
            startActivity(intent1)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initData()
    }

    private fun initData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        loginbt.setOnClickListener(this)
    }
}

