package com.satoruchannel.jokenpokemon.ui

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.satoruchannel.jokenpokemon.JokenpoAppCompatActivity
import com.satoruchannel.jokenpokemon.R
import com.satoruchannel.jokenpokemon.adapter.PontuacaoAdapter
import com.satoruchannel.jokenpokemon.entity.Pontuacao
import com.satoruchannel.jokenpokemon.entity.PontuacaoResponse
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class RankingActivity : JokenpoAppCompatActivity() {

//    lateinit var rankingViewModel: RankingViewModel

    val CONNECTION_TIMEOUT: Int = 30000
    val READ_TIMEOUT: Int = 35000
    private lateinit var mAdapter: PontuacaoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        AsyncFetch().execute("https://gamestjd.herokuapp.com/jokenpokemon/pontuacao")

//        rankingViewModel = ViewModelProviders.of(this).get(RankingViewModel::class.java)
//        rankingViewModel.pesquisarPontuacao()
//
//        rankingViewModel.apiResponse.observe(this, Observer { api ->
//            if (api?.erro == null) {
//                Log.i("TAG", "SUCESSO")
//                Log.i("TAG", "ID: ${api!!.pontuacoes!![0].id}\n" +
//                        "Nome: ${api!!.pontuacoes!![0].nome}\n" +
//                        "Pontuação: ${api!!.pontuacoes!![0].pontos}")
//            } else {
//                Log.i("TAG", "ERRO${api.erro}")
//            }
//        })

//        rankingViewModel.isLoading.observe(this, Observer { isLoading ->
//            if (isLoading!!) {
//                loading.visibility = View.VISIBLE
//            } else {
//                loading.visibility = View.GONE
//            }
//        })
    }

    //Params: Tipo do Objeto que será passado por parâmetro, caso não precise mandar algum parâmetro, colocar colo "Void";
    //Progress: Tipo do Objeto usado para informar o progresso do processo, utilizados no método publishProgress(Progress...) e onProgressUpdate(Progress...);
    //Result: Tipo do Objeto de Retorno. Usado no método onInBackground(Params...) como sendo o seu retorno e o onPostExecute(Result).
    private inner class AsyncFetch : AsyncTask<String, String, String>() {
        val pdLoading = ProgressDialog(this@RankingActivity)
        var conn: HttpURLConnection? = null
        var url: URL? = null

        override fun onPreExecute() {
            super.onPreExecute()

            // this method will be running on UI thread
            pdLoading.setMessage("\tLoading...")
            pdLoading.setCancelable(false)
            pdLoading.show()
        }

        // Este método já é chamado após o método onPreExecute() ser finalizado.
        // É nele que o processo será realizado em uma thread separada da Thread Principal;
        // Aqui você também poderá chamar o método publishProgress(Progress...) informando o valor
        // de andamento do seu processo, o mesmo, quando chamado,
        // irá executar o onProgressUpdate(Progress...)
        // passando esse valor que você informou no publishProgress(Progress...);
        // Também é aconcelhável que, no método, seja validado sempre o método isCancelled()
        // (este método retornará TRUE caso sejá chamado o método cancel()) para, caso retornar TRUE,
        // você realize a parada do seu processamento.
        override fun doInBackground(vararg params: String?): String {
            try {
                url = URL(params[0])
            } catch (ex: MalformedURLException) {
                ex.printStackTrace()
                return ex.toString()
            }

            try {
                // Setup HttpURLConnection class to send and receive data
                conn = url!!.openConnection() as HttpURLConnection
                conn?.readTimeout = READ_TIMEOUT
                conn?.connectTimeout = CONNECTION_TIMEOUT
                conn?.requestMethod = "GET"

                // DoOutput to true as we receive data from json file
                // conn?.doOutput = true

                conn?.connect()
            } catch (ex: IOException) {
                ex.printStackTrace()
                return ex.toString()
            }

            try {
                if (conn?.responseCode == HttpURLConnection.HTTP_OK) { // 200
                    // Realiza a leitura dos dados do servidor
                    val input = conn?.inputStream
                    val reader = BufferedReader(InputStreamReader(input))
                    val result = StringBuilder()
                    var line: String? = reader.readLine()

                    while (line != null) {
                        result.append(line)

                        line = reader.readLine()
                    }

                    return result.toString()
                } else {
                    return "unsuccessful"
                }
            } catch (ex: IOException) {
                ex.printStackTrace()
                return ex.toString()
            }
        }

        override fun onPostExecute(result: String?) {
            // Este método roda na UI thread
            pdLoading.dismiss()
            val data = ArrayList<Pontuacao>()

            pdLoading.dismiss()
            try {
                val jArray = JSONArray(result)
                val qtdPontuacao = jArray.length() - 1
                for (i in 0..qtdPontuacao) {
                    val jsonData = jArray.getJSONObject(i);
                    val p = Pontuacao(
                        "",
                        jsonData.getString(PontuacaoResponse.TAG_NOME),
                        jsonData.getString(PontuacaoResponse.TAG_PONTOS)
                    )

                    data.add(p);
                }
                setUpRecyclerView(data);
            } catch (ex: JSONException) {
                Log.i("TAG", ex.toString())
                Toast.makeText(this@RankingActivity, ex.toString(), Toast.LENGTH_LONG).show()
            }
        }

        private fun setUpRecyclerView(data: List<Pontuacao>) {
            val rcLista: RecyclerView = findViewById(R.id.rcLista)
            mAdapter = PontuacaoAdapter(this@RankingActivity, data)
            rcLista.adapter = mAdapter
            rcLista.layoutManager = LinearLayoutManager(this@RankingActivity)

//            mAdapter.clickListener = OnItemClickListener() {
//                override fun onClick(view: View, position: Int) {
//                    val p: Pontuacao = mAdapter.getItem(position)
//                    val intent = Intent(this@RankingActivity, DetalheActivity::class.java)
//                }
//            }
        }

    }
}