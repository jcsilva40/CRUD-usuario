package com.stfn2.ggas.tools;

import java.io.File;

public class ToolFiles {

    public static void criarPastaSeNaoExistir(String path) {
        if (!pastaExiste(path)) {
            File diretorio = new File(path);
            diretorio.mkdirs(); // mkdir() cria somente um diretório, mkdirs()
            // cria diretórios e subdiretórios.
        }
    }

    public static boolean pastaExiste(String path) {
        File diretorio = new File(path);
        return diretorio.exists();
    }
}
