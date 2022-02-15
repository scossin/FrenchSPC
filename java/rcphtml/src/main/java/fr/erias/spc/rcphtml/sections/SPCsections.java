package fr.erias.spc.rcphtml.sections;

/**
 * The list of all SPC HTML sections, where it begins, where it ends
 * 
 * @author Sebastien Cossin
 *
 */
public enum SPCsections {
	
	RcpDenomination("1. DENOMINATION DU MEDICAMENT","RcpDenomination",1,null),
	RcpCompoQualiQuanti("2. COMPOSITION QUALITATIVE ET QUANTITATIVE","RcpCompoQualiQuanti",1,null),
	RcpFormePharm("3. FORME PHARMACEUTIQUE","RcpFormePharm",1,null),
	RcpIndicTherap("4.1 Indications thérapeutiques","RcpIndicTherap",2,"4.2"),
	RcpPosoAdmin("4.2 Posologie et mode d'administration","RcpPosoAdmin",2,"4.3"),
	RcpContreindications("4.3 Contre-indications","RcpContreindications",2,"4.4"),
	RcpMisesEnGarde("4.4. Mises en garde spéciales et précautions d'emploi","RcpMisesEnGarde",2,"4.5"),
	RcpInteractionsMed("4.5 Interactions avec d'autres médicaments et autres formes d'interactions","RcpInteractionsMed",2,"4.6"),
	RcpFertGrossAllait("4.6 Fertilité, grossesse et allaitement","RcpFertGrossAllait",2,"4.7"),
	RcpConduite("4.7 Effets sur l'aptitude à conduire des véhicules et à utiliser des machines","RcpConduite",2,"4.8"),
	RcpEffetsIndesirables("4.8 Effets indésirables","RcpEffetsIndesirables",2,"4.9"), // 
	RcpSurdosage("4.9 Surdosage","RcpSurdosage",2,null),
	RcpPropPharmacodynamiques("5.1 Propriétés pharmacodynamiques","RcpPropPharmacodynamiques",2,"5.2"),
	RcpPropPharmacocinetiques("5.2 Propriétés pharmacocinétiques","RcpPropPharmacocinetiques",2,"5.3"),
	RcpSecuritePreclinique("5.3 Données de sécurité préclinique","RcpSecuritePreclinique",1,null),
	RcpListeExcipients("6.1 Liste des excipients","RcpListeExcipients",2,"6.2"),
	RcpIncompatibilites("6.2 Incompatibilités","RcpIncompatibilites",2,"6.3"),
	RcpDureeConservation("6.3 Durée de conservation","RcpDureeConservation",2,"6.4"),
	RcpPrecConservation("6.4 Précautions particulières de conservation","RcpPrecConservation",2,"6.5"),
	RcpEmballage("6.5 Nature et contenu de l'emballage extérieur","RcpEmballage",2,"6.6"),
	RcpPrecEmpl("6.6 Précautions particulières d’élimination et de manipulation","RcpPrecEmpl",1,null),
	RcpTitulaireAmm("7. TITULAIRE DE L’AUTORISATION DE MISE SUR LE MARCHE","RcpTitulaireAmm",1,null),
	RcpNumAutor("8. NUMERO(S) D’AUTORISATION DE MISE SUR LE MARCHE","RcpNumAutor",1,null),
	RcpPremiereAutorisation("9. DATE DE PREMIERE AUTORISATION/DE RENOUVELLEMENT DE L’AUTORISATION","RcpPremiereAutorisation",1,null),
	RcpDateRevision("10. DATE DE MISE A JOUR DU TEXTE","RcpDateRevision",1,null),
	RcpDosimetrie("11. DOSIMETRIE","RcpDosimetrie",1,null),
	RcpInstPrepRadioph("12. INSTRUCTIONS POUR LA PREPARATION DES RADIOPHARMACEUTIQUES","RcpInstPrepRadioph",1,null);
	
	/**
	 * The text value of the section (ex: 4.1 Indication therapeutiques)
	 * @return
	 */
	private String sectionLabel; // what the user sees on the page
	
	/**
	 * In HTML only, the value of name attribute (ex: RcpIndic)
	 * @return
	 */
	private String sectionName; // <a name=''> in the HTML file
	
	/**
	 * HTML only, level of the current element (ex: 2 for AmmAnnexeTitre2)
	 * @return
	 */
	private int level; // 4. => level 1 ; 4.1 => level 2 etc... 

	/**
	 * Section id like 4.1
	 * @return
	 */
	public String getSectionId() {
		return(this.sectionLabel.substring(0, 3));
	}
	
	private String nextSectionId; // 4.2 (to know where it ends)
	
	public String getSectionName() {
		return sectionName;
	}

	public String getSectionLabel() {
		return sectionLabel;
	}
	
	public SPCsections getNextSection() {
		for (SPCsections section : SPCsections.values()){
			if (section.getSectionId().equals(this.nextSectionId)) {
				return(section);
			}
		}
		return null;
	}

	public int getLevel() {
		return(this.level);
	}

	private SPCsections(String sectionLabel, String sectionName, int level, String nextSection) {
		this.sectionLabel = sectionLabel;
		this.sectionName = sectionName;
		this.level = level;
		this.nextSectionId = nextSection;
	}
}
