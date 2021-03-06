package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.annotation.NfeObjeto;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.com.samuelweb.nfe.util.validators.impl.ValidarChaveAcesso;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;
import org.apache.commons.lang3.StringUtils;

public class NfRef {

    @NfeCampo(tipo = String.class,
            id = "B13", tag = "refNFe",
            tamanhoMinimo = 44, tamanhoMaximo = 44, ocorrencias = 0,
            descricao = DfeConsts.DSC_REFNFE, validadores = {ValidarChaveAcesso.class})
    private String refNFe;

    @NfeObjeto(id = "BA03", tag = "refNF"
            , ocorrencias = 0, descricao = NfeConsts.DSC_REFNF)
    private RefNf refNF;

    @NfeObjeto(id = "BA10", tag = "refNFP"
            , ocorrencias = 0, descricao = NfeConsts.DSC_REFNFP)
    private RefNfp refNFP;

    @NfeCampo(tipo = String.class,
            id = "B20i", tag = "refCTe",
            tamanhoMinimo = 44, tamanhoMaximo = 44, ocorrencias = 0,
            descricao = DfeConsts.DSC_REFCTE, validadores = {ValidarChaveAcesso.class})
    private String refCTe;

    @NfeObjeto(id = "BA20", tag = "refECF"
            , ocorrencias = 0, descricao = NfeConsts.DSC_REFECF)
    private RefECF refECF;

    public TNFe.InfNFe.Ide.NFref build() {
        TNFe.InfNFe.Ide.NFref nfRef = new TNFe.InfNFe.Ide.NFref();
        nfRef.setRefNFe(this.getRefNFe());
        if (this.getRefNF() != null) {
            nfRef.setRefNF(this.getRefNF().build());
        }
        if (this.getRefNFP() != null) {
            nfRef.setRefNFP(this.getRefNFP().build());
        }
        nfRef.setRefCTe(this.getRefCTe());
        if (this.getRefECF() != null) {
            nfRef.setRefECF(this.getRefECF().build());
        }
        return nfRef;
    }

    public String getRefNFe() {
        return refNFe;
    }

    public void setRefNFe(String refNFe) {
        this.refNFe = refNFe;
    }

    public RefNf getRefNF() {
        return refNF;
    }

    public void setRefNF(RefNf refNF) {
        this.refNF = refNF;
    }

    public RefNfp getRefNFP() {
        return refNFP;
    }

    public void setRefNFP(RefNfp refNFP) {
        this.refNFP = refNFP;
    }

    public String getRefCTe() {
        return refCTe;
    }

    public void setRefCTe(String refCTe) {
        this.refCTe = refCTe;
    }

    public RefECF getRefECF() {
        return refECF;
    }

    public void setRefECF(RefECF refECF) {
        this.refECF = refECF;
    }

    public void validarRegraNegocio(InfNFe infNFe) {
        if (StringUtils.isNotBlank(this.getRefNFe())) {
            this.setRefNF(null);
            this.setRefNFP(null);
            this.setRefECF(null);
        } else if (this.getRefNF() != null) {
            this.getRefNF().validarRegraNegocio(infNFe);
            this.setRefNFP(null);
            this.setRefECF(null);
        } else if (this.getRefNFP() != null) {
            this.getRefNFP().validarRegraNegocio(infNFe);
            this.setRefECF(null);
        }
        if (this.getRefECF() != null) {
            this.getRefECF().validarRegraNegocio(infNFe);
        }
    }
}
