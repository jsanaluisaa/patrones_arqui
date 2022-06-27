/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Inquilinos;
import java.util.List;

/**
 *
 * @author PXNDX
 */
public interface DaoInquilinos {
    public List <Inquilinos> inquilinosSel();
    public Inquilinos inquilinosGet(Integer id);
    public String inquilinosIns(Inquilinos inquilino);
    public String inquilinosUpd(Inquilinos inquilino);
    public String inquilinosDel(List<Integer> ids);
    public String getMessage();
}
