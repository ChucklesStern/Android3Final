package com.mysampleapp.provider.apicalls;

import android.content.Context;
import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
/**
 * Created by root on 5/29/17.
 */
//extends Async task Api24 only
public class BackgroundApi extends AsyncTask<String, Void, String> {
    Context context;

    BackgroundApi (Context ctx) {
        context = ctx;
    }

    //create callback
    public Createtransaction.AsyncResponse delegate = null;

    //assigning callback interface
    public BackgroundApi(Createtransaction.AsyncResponse asyncResponse) {
        delegate = asyncResponse;
    }

    @Override
    protected String doInBackground(String... params)  {
        String type = params[0];
        String endpointUrl = "https://q1khxagpca.execute-api.us-east-1.amazonaws.com/test/";
        //THis is to ADD USERS
        if(type.equals("createtransaction")) try {
            String Description = params[1];
            String preAmount = params[2];
            double AmountPrepper = Double.valueOf(preAmount);
            //   long Amount = (long) AmountPrepper;
            URL url = new URL(endpointUrl + type);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            JSONObject object = new JSONObject();
            object.put("Description", Description);
            object.put("Amount", AmountPrepper);

            Log.d("Sending to Server:", (String) object.toString());
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStreamWriter wr = new OutputStreamWriter(httpURLConnection.getOutputStream());
            wr.write(String.valueOf(object));
            wr.close();

            //response
            InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "iso-8859-1"));
            String resultPrep = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                resultPrep += line;
            }

            int resultChecka = resultPrep.length();
            String resultCheckSa = Integer.toString(resultChecka);
            Log.d("resultCheck Length:", (String) resultCheckSa);

            if (resultPrep.length() < 3) {
                resultPrep = "User not added";
                String result = resultPrep.toString();

                in.close();
                httpURLConnection.disconnect();

                return result;
            } else if ((resultPrep.length() > 2)) {
                int resultCheck = resultPrep.length();
                String resultCheckS = Integer.toString(resultCheck);
                Log.d("resultCheck Length:", (String) resultCheckS);
                //   JSONArray result = new JSONArray(resultPrep);
                // Log.d("from Server 1A:", (String) result.toString());
                String result = resultPrep.toString();

                in.close();
                httpURLConnection.disconnect();


                return result.toString();
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(type.equals("fingetall")) try {
            String Description = params[1];
            String preAmount = params[2];

            //   long Amount = (long) AmountPrepper;
            URL url = new URL(endpointUrl + type);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            JSONObject object = new JSONObject();
            object.put("Description", Description);
            object.put("Amount", preAmount);

            Log.d("Sending to Server:", (String) object.toString());
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStreamWriter wr = new OutputStreamWriter(httpURLConnection.getOutputStream());
            wr.write(String.valueOf(object));
            wr.close();

            //response
            InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "iso-8859-1"));
            String resultPrep = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                resultPrep += line;
            }

            int resultChecka = resultPrep.length();
            String resultCheckSa = Integer.toString(resultChecka);
            Log.d("resultCheck Length:", (String) resultCheckSa);

            if (resultPrep.length() < 3) {
                resultPrep = "User not added";
                String result = resultPrep.toString();

                in.close();
                httpURLConnection.disconnect();

                return result;
            } else if ((resultPrep.length() > 2)) {
                int resultCheck = resultPrep.length();
                String resultCheckS = Integer.toString(resultCheck);
                Log.d("resultCheck Length:", (String) resultCheckS);
                //   JSONArray result = new JSONArray(resultPrep);
                // Log.d("from Server 1A:", (String) result.toString());
                String result = resultPrep.toString();

                in.close();
                httpURLConnection.disconnect();


                return result.toString();
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(type.equals("/product/get")) try {
            String Description = params[1];
            String preAmount = params[2];

            //   long Amount = (long) AmountPrepper;
            URL url = new URL(endpointUrl + type);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            JSONObject object = new JSONObject();
            object.put("Description", Description);
            object.put("Amount", preAmount);

            Log.d("Sending to Server:", (String) object.toString());
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStreamWriter wr = new OutputStreamWriter(httpURLConnection.getOutputStream());
            wr.write(String.valueOf(object));
            wr.close();

            //response
            InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "iso-8859-1"));
            String resultPrep = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                resultPrep += line;
            }

            int resultChecka = resultPrep.length();
            String resultCheckSa = Integer.toString(resultChecka);
            Log.d("resultCheck Length:", (String) resultCheckSa);

            if (resultPrep.length() < 3) {
                resultPrep = "User not added";
                String result = resultPrep.toString();

                in.close();
                httpURLConnection.disconnect();

                return result;
            } else if ((resultPrep.length() > 2)) {
                int resultCheck = resultPrep.length();
                String resultCheckS = Integer.toString(resultCheck);
                Log.d("resultCheck Length:", (String) resultCheckS);
                //   JSONArray result = new JSONArray(resultPrep);
                // Log.d("from Server 1A:", (String) result.toString());
                String result = resultPrep.toString();

                in.close();
                httpURLConnection.disconnect();


                return result.toString();
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(type.equals("findelete")) try {
            String DeleteIdString = params[1];
            //    String preAmount = params[2];
            double DeleteId = Double.parseDouble(DeleteIdString);
            //   long Amount = (long) AmountPrepper;
            URL url = new URL(endpointUrl + type);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            JSONObject object = new JSONObject();
            object.put("TransactionId", DeleteId);
            //  object.put("Amount", preAmount);

            Log.d("Sending to Server:", (String) object.toString());
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStreamWriter wr = new OutputStreamWriter(httpURLConnection.getOutputStream());
            wr.write(String.valueOf(object));
            wr.close();

            //response
            InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "iso-8859-1"));
            String resultPrep = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                resultPrep += line;
            }

            int resultChecka = resultPrep.length();
            String resultCheckSa = Integer.toString(resultChecka);
            Log.d("resultCheck Length:", (String) resultCheckSa);

            if (resultPrep.length() < 3) {
                resultPrep = "User not added";
                String result = resultPrep.toString();

                in.close();
                httpURLConnection.disconnect();

                return result;
            } else if ((resultPrep.length() > 2)) {
                int resultCheck = resultPrep.length();
                String resultCheckS = Integer.toString(resultCheck);
                Log.d("resultCheck Length:", (String) resultCheckS);
                //   JSONArray result = new JSONArray(resultPrep);
                // Log.d("from Server 1A:", (String) result.toString());
                String result = resultPrep.toString();

                in.close();
                httpURLConnection.disconnect();


                return result.toString();
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(type.equals("finupdate")) try {
            String Id = params[1];
            int ID = Integer.valueOf(Id);
            String Description = params[2];
            String preAmount = params[3];
            double AmountPrepper = Double.valueOf(preAmount);
            //   long Amount = (long) AmountPrepper;
            URL url = new URL(endpointUrl + type);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            JSONObject object = new JSONObject();
            object.put("TransactionId", ID);
            object.put("Description", Description);
            object.put("Amount", AmountPrepper);

            Log.d("Sending to Server:", (String) object.toString());
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStreamWriter wr = new OutputStreamWriter(httpURLConnection.getOutputStream());
            wr.write(String.valueOf(object));
            wr.close();

            //response
            InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "iso-8859-1"));
            String resultPrep = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                resultPrep += line;
            }

            int resultChecka = resultPrep.length();
            String resultCheckSa = Integer.toString(resultChecka);
            Log.d("resultCheck Length:", (String) resultCheckSa);

            if (resultPrep.length() < 3) {
                resultPrep = "User not added";
                String result = resultPrep.toString();

                in.close();
                httpURLConnection.disconnect();

                return result;
            } else if ((resultPrep.length() > 2)) {
                int resultCheck = resultPrep.length();
                String resultCheckS = Integer.toString(resultCheck);
                Log.d("resultCheck Length:", (String) resultCheckS);
                //   JSONArray result = new JSONArray(resultPrep);
                // Log.d("from Server 1A:", (String) result.toString());
                String result = resultPrep.toString();

                in.close();
                httpURLConnection.disconnect();


                return result.toString();
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*
//This is for Login or USER Retrieval
        if(type.equals("username")) try {
            String email1 = params[1];
            String password1 = params[2];
            String firstname1 = params[3];
            String lastname = params[4];
            String telephone = params[5];
            URL url = new URL(endpointUrl + type);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            JSONObject object = new JSONObject();
            object.put("email", email1);
            object.put("password", password1);
            Log.d("Sending to Server:", (String) object.toString());
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStreamWriter wr = new OutputStreamWriter(httpURLConnection.getOutputStream());
            wr.write(String.valueOf(object));
            wr.close();

            //response
            InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "iso-8859-1"));
            String resultPrep = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                resultPrep += line;
            }


            if (resultPrep.length() < 3) {
                resultPrep = "User not found";
                String result = resultPrep;

                in.close();
                httpURLConnection.disconnect();
                return result;
            } else if ((resultPrep.length() > 2)) {
                int resultCheck = resultPrep.length();
                String resultCheckS = Integer.toString(resultCheck);
                Log.d("resultCheck Length:", (String) resultCheckS);
                //   JSONArray result = new JSONArray(resultPrep);
                // Log.d("from Server 1A:", (String) result.toString());
                String result = resultPrep;

                in.close();
                httpURLConnection.disconnect();


                return result.toString();
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        */
        return null;

    }

    @Override
    protected void onPostExecute(String result) {
        String endResult= result;
        Log.d("from Server 1B:", (String) endResult);
        delegate.processFinish(endResult);

    }
}

