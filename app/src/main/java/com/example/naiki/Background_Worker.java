package com.example.naiki;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Background_Worker extends AsyncTask<String,Void,String> {

    String type;
    Context context;
    AlertDialog alertDialog;
    SharedPreferences sharedPreferences;

    Background_Worker(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        type = params[0];

        if (type.equals("login")) {
            try {
                String login_url = "http://192.168.56.1/naiki/login.php";
                String un = params[1];
                String ps = params[2];

                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("un", "UTF-8") + "=" + URLEncoder.encode(un, "UTF-8") + "&"
                        + URLEncoder.encode("ps", "UTF-8") + "=" + URLEncoder.encode(ps, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if (type.equals("mlogin")) {
            try {
                String login_url = "http://lms-php.000webhostapp.com/LMS_PHP/mlogin.php";
                String mun = params[1];
                String mps = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("mun", "UTF-8") + "=" + URLEncoder.encode(mun, "UTF-8") + "&"
                        + URLEncoder.encode("mps", "UTF-8") + "=" + URLEncoder.encode(mps, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if (type.equals("alogin")) {
            try {
                String login_url = "http://lms-php.000webhostapp.com/LMS_PHP/alogin.php";
                String aun = params[1];
                String aps = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("aun", "UTF-8") + "=" + URLEncoder.encode(aun, "UTF-8") + "&"
                        + URLEncoder.encode("aps", "UTF-8") + "=" + URLEncoder.encode(aps, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (type.equals("applyleave")) {
            String reg_url = "http://lms-php.000webhostapp.com/LMS_PHP/applyleave.php";
            String nt = params[1];
            String sd = params[2];
            String ed = params[3];
            String lt = params[4];
            String eid = params[5];
            String mid = params[6];


            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

                String post_data =
                        URLEncoder.encode("nt", "UTF-8") + "=" + URLEncoder.encode(nt, "UTF-8") +
                                "&" + URLEncoder.encode("sd", "UTF-8") + "=" + URLEncoder.encode(sd, "UTF-8") +
                                "&" + URLEncoder.encode("ed", "UTF-8") + "=" + URLEncoder.encode(ed, "UTF-8") +
                                "&" + URLEncoder.encode("lt", "UTF-8") + "=" + URLEncoder.encode(lt, "UTF-8") +
                                "&" + URLEncoder.encode("eid", "UTF-8") + "=" + URLEncoder.encode(eid, "UTF-8") +
                                "&" + URLEncoder.encode("mid", "UTF-8") + "=" + URLEncoder.encode(mid, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStreamWriter.close();
                outputStream.close();


                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = "";
                String result = "";

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();

                httpURLConnection.disconnect();


                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        //REGISTER CODE
        if (type.equals("register")) {
            String reg_url = "http://192.168.56.1/naiki/register.php";
            String un = params[1];
            String ps = params[2];
            String ad = params[3];
            String ph = params[4];
            String em = params[5];
            String tp = params[6];


            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

                String post_data =
                        URLEncoder.encode("un", "UTF-8") + "=" + URLEncoder.encode(un, "UTF-8") +
                                "&" + URLEncoder.encode("ps", "UTF-8") + "=" + URLEncoder.encode(ps, "UTF-8") +
                                "&" + URLEncoder.encode("ad", "UTF-8") + "=" + URLEncoder.encode(ad, "UTF-8") +

                                "&" + URLEncoder.encode("ph", "UTF-8") + "=" + URLEncoder.encode(ph, "UTF-8") +
                                "&" + URLEncoder.encode("tp", "UTF-8") + "=" + URLEncoder.encode(tp, "UTF-8") +
                                "&" + URLEncoder.encode("em", "UTF-8") + "=" + URLEncoder.encode(em, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStreamWriter.close();
                outputStream.close();


                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = "";
                String result = "";

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();

                httpURLConnection.disconnect();

                alertDialog.setTitle("Registration Status");
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return null;

    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Alert Message");

    }


    @Override
    protected void onPostExecute(String s) {
        if (s != null) {

            String tt = type;


            if (type.equals("login")) {

                if (s.equals("Login-Failed")) {
                    alertDialog.setMessage("Invalid username or password");
                    alertDialog.show();
                } else {

                    try {
                        sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);

                        JSONObject jobj = new JSONObject(s);
                        int uphon = jobj.getInt("uphone");
                        String nam = jobj.getString("uname");
                        String pas = jobj.getString("upass");

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("uphone", uphon);
                        editor.putString("uname", nam);
                        editor.putString("upass", pas);
                        editor.commit();

                        Intent intent = new Intent(context, Dashboard.class);
                        context.startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }


            if (type.equals("register")) {

                if (s.equals("Register-Filed")) {
                    alertDialog.setMessage("Regsiteration Failed / Phone Number already Exists '\n' Please Login");
                    alertDialog.show();
                } else {
                    alertDialog.setMessage("Registered Successfully");
                    alertDialog.show();
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    }
                }
            }


        }

    }





