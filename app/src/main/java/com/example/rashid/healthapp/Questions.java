package com.example.rashid.healthapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;

public class Questions {

    /* Saves the questions from json files into arraylist */
    private ArrayList<String> questionList = new ArrayList<String>();

    /* Saves all individual options per question from json files into arraylist */
    private ArrayList<String> perQuestionOptions;

    /* Saves the arraylist into another arraylist so that it becomes easier to find all option for each question based on their index.
     * After every insertion "perQuestionOptions" will be set to null */
    private ArrayList< ArrayList<String> > optionList = new ArrayList<>();

    /* Saves the max number of options each individual question can have*/
    private int max_number_of_options = 4;

    public Questions(InputStream is) {

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        JSONArray jarrr = parse_json(br);

        if(jarrr != null) System.out.println(jarrr.length());

        for (int i=0;i<jarrr.length();i++){

            String question = "";
            String options = "";
            perQuestionOptions = new ArrayList<String>();

            try {

                JSONObject ob = (JSONObject) jarrr.get(i);

                question = ""+ob.get("question");

                questionList.add(question);

                for (int j = 0; j<max_number_of_options;j++ ){

                    String index="option_"+(j+1);

                    options = (String) ob.get(index);

                    //Checks if the value from the key value pair is "_" or not. If not then add in the list otherwise don't.
                    if (!(options.equals("_"))) perQuestionOptions.add(options);
                }

                optionList.add(perQuestionOptions);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        System.out.println(optionList);

    }

    public JSONArray parse_json(BufferedReader br){

        String line="";
        String data="";
        JSONArray jsnarr = null;

        while(line != null){

            try {
                // Reading each line from the json file and adding them in data variable.
                line = br.readLine();
                data += line;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        try {

            // The data variable now contains the full json data, but its stored as a string.
            jsnarr = new JSONArray(data);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsnarr;

    }

    public ArrayList<String> getQuestionList() {
        return questionList;
    }

    public ArrayList<ArrayList<String>> getOptionList() {
        return optionList;
    }

    public int getMax_number_of_options() {
        return max_number_of_options;
    }

    public void setMax_number_of_options(int max_number_of_options) {
        this.max_number_of_options = max_number_of_options;
    }
}