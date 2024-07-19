package br.com.alura.projetos.demo.tools;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;
import java.io.IOException;

public class GoogleIA {
    private static final String PROJECT_ID = "screenmatch-alura";
    private static final String LOCATION  ="us-central1";
    private static final String MODEL = "gemini-1.5-pro-001";
    private static VertexAI vertexAI = new VertexAI(PROJECT_ID,LOCATION);

    public static String translatePrompt(String text) throws IOException {
        String prompt = "Translate this text to Brazilian Portuguese: " + text;
        GenerativeModel model = new GenerativeModel(MODEL,vertexAI);
        GenerateContentResponse response = model.generateContent(prompt);
        return ResponseHandler.getText(response);
    }

}
