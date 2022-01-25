
package com.example.jsontutorial.JSON;
//import static android.os.Build.VERSION_CODES.R;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.jsontutorial.R;
import com.example.jsontutorial.beans.Company;
import com.example.jsontutorial.beans.Address;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;


public class ReadJSON {

    // Read the company.json file and convert it to a java object.
    public static Vector<Company> readCompanyJSONFile(Context context) throws IOException,JSONException {

        // Read content of company.json

       String jsonText = readText(context, R.raw.comp);
       Vector<Company> cmps = new Vector<Company>();
        JSONObject json = new JSONObject(jsonText);
        JSONArray NV ;
        NV=json.getJSONArray("users");

        for (int i = 0; i < NV.length(); i++) {
            JSONObject jsonRoot ;
            jsonRoot = NV.getJSONObject(i);
            int id= jsonRoot.getInt("id");
            String name = jsonRoot.getString("name");

            JSONArray jsonArray = jsonRoot.getJSONArray("websites");
            String[] websites = new String[jsonArray.length()];

            for(int j=0;j < jsonArray.length();j++) {
                websites[j] = jsonArray.getString(j);
            }

            JSONObject jsonAddress = jsonRoot.getJSONObject("address");
            String street = jsonAddress.getString("street");
            String city = jsonAddress.getString("city");
            Address address= new Address(street, city);

            Company company = new Company();
            company.setId(id);
            company.setName(name);
            company.setAddress(address);
            company.setWebsites(websites);
            cmps.add(company);
        }


        return cmps;
    }



    private static String readText(Context context, int resId) throws IOException {
        InputStream is = context.getResources().openRawResource(resId);
        BufferedReader br= new BufferedReader(new InputStreamReader(is));
        StringBuilder sb= new StringBuilder();
        String s= null;
        while((  s = br.readLine())!=null) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

}
