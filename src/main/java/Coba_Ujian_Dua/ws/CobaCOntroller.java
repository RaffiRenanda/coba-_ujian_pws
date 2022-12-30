/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coba_Ujian_Dua.ws;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author TUF GAMING
 */
@Controller
@ResponseBody
public class CobaCOntroller {
    Buah data = new Buah();
    BuahJpaController actrl = new BuahJpaController();
    
    @RequestMapping("/getBuah")
    public List<Buah> getBuah(){
        return actrl.findBuahEntities();
       
    }
    
    @RequestMapping ("/delBuah/{id}")
    public String delBuah (@PathVariable("id")int id){
        try
        {
            actrl.destroy(id);
            return "Sukses menghapus";
            
        }
        catch (Exception error)
                {
                    return "Id Tidak ditemukan";
                }
    }
    
}
