from pydantic import BaseModel


class RCP(BaseModel):
    RcpDenomination: str
    RcpCompoQualiQuanti: str
    RcpFormePharm: str
    RcpIndicTherap: str
    RcpPosoAdmin: str
    RcpContreindications: str
    RcpMisesEnGarde: str
    RcpInteractionsMed: str
    RcpFertGrossAllait: str
    RcpConduite: str
    RcpEffetsIndesirables: str
    RcpSurdosage: str
    RcpPropPharmacodynamiques: str
    RcpPropPharmacocinetiques: str
    RcpSecuritePreclinique: str
    RcpListeExcipients: str
    RcpIncompatibilites: str
    RcpDureeConservation: str
    RcpPrecConservation: str
    RcpEmballage: str
    RcpPrecEmpl: str
    RcpTitulaireAmm: str
    RcpNumAutor: str
    RcpPremiereAutorisation: str
    RcpDateRevision: str
    RcpDosimetrie: str
    RcpInstPrepRadioph: str


class RCPHTML(RCP):
    cis: str


class RCPPDF(RCP):
    numCode: str
