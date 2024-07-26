package com.example.spinner;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.LinkedHashMap;

public class MainActivity extends AppCompatActivity
{
    private Spinner spinner;
    private Button buttonRegister;
    private TextView textViewLog;
    private Button buttonBorrar;
    private LinkedHashMap<String, String> registro;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        buttonRegister = findViewById(R.id.button);
        textViewLog = findViewById(R.id.textView_log);
        buttonBorrar = findViewById(R.id.buttonBorrar);

        registro = new LinkedHashMap<>();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Spinner_option, android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        buttonRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String seleccion = spinner.getSelectedItem().toString();
                registrarSeleccion(seleccion);
            }
        });

        buttonBorrar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String seleccion = spinner.getSelectedItem().toString();
                borrarSeleccion(seleccion);
            }
        });
    }

    private void registrarSeleccion(String seleccion)
    {
        registro.put(seleccion, ":");

        actualizarRegistro();
    }

    private void borrarSeleccion(String seleccion)
    {
        if (!registro.isEmpty())
        {
            String lastKey = null;
            for (String key : registro.keySet())
            {
                lastKey = key;
            }
            if (lastKey != null)
            {
                registro.remove(lastKey);
            }
            actualizarRegistro();
        }
    }

    private void actualizarRegistro()
    {
        StringBuilder registroText = new StringBuilder("Registro:\n");
        for (String key : registro.keySet())
        {
            registroText.append(key).append(": ").append(registro.get(key)).append("\n");
        }
        textViewLog.setText(registroText.toString());
    }
}