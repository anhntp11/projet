package fr.orleans.m1s1miage.group4.backend.controleur;

import fr.orleans.m1s1miage.group4.backend.model.dto.error.ErrorDTO;
import fr.orleans.m1s1miage.group4.backend.model.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Cette class prend toutes les exceptions levées par les controllers et les gère ici, de facon propre et centralisé.
 * Elle permet aussi de renvoyer des details sur l'erreur grace a un ErrorDTO
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // PROBLEME DANS LA REQUETE =======

    @ExceptionHandler(InformationManquanteException.class)
    public ResponseEntity<ErrorDTO> informationManquanteException(InformationManquanteException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new ErrorDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(InformationIncorrecteException.class)
    public ResponseEntity<ErrorDTO> informationIncorrecteException(InformationIncorrecteException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new ErrorDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(EmpruntPossessionException.class)
    public ResponseEntity<ErrorDTO> empruntPossessionException(EmpruntPossessionException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new ErrorDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(EmpruntDejaEtenduException.class)
    public ResponseEntity<ErrorDTO> empruntDejaEtenduException(EmpruntDejaEtenduException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new ErrorDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(DemandeDejaTraiteeException.class)
    public ResponseEntity<ErrorDTO> demandeDejaTraiteeException(DemandeDejaTraiteeException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new ErrorDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(DateEcheanceEmpruntDepasseeException.class)
    public ResponseEntity<ErrorDTO> dateEcheanceEmpruntDepasseeException(DateEcheanceEmpruntDepasseeException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new ErrorDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(AdminSuppressionException.class)
    public ResponseEntity<ErrorDTO> adminSuppressionException(AdminSuppressionException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new ErrorDTO(e.getMessage(), status.value()));
    }



    // DEJA EXISTANT =======

    @ExceptionHandler(BuDejaExistanteException.class)
    public ResponseEntity<ErrorDTO> buDejaExistanteException(BuDejaExistanteException e) {
        HttpStatus status = HttpStatus.CONFLICT;
        return ResponseEntity.status(status).body(new ErrorDTO(e.getMessage(), status.value()));
    }

    // INCONNU =======

    @ExceptionHandler(BuInconnueException.class)
    public ResponseEntity<ErrorDTO> buInconnueException(BuInconnueException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(new ErrorDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(GenreInconnuException.class)
    public ResponseEntity<ErrorDTO> genreInconnuException(GenreInconnuException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(new ErrorDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(LivreInconnuException.class)
    public ResponseEntity<ErrorDTO> livreInconnuException(LivreInconnuException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(new ErrorDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(InformationBUInconnueException.class)
    public ResponseEntity<ErrorDTO> informationBUInconnueException(InformationBUInconnueException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(new ErrorDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(EtudiantInconnuException.class)
    public ResponseEntity<ErrorDTO> etudiantInconnuException(EtudiantInconnuException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(new ErrorDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(UtilisateurInconnuException.class)
    public ResponseEntity<ErrorDTO> utilisateurInconnuException(UtilisateurInconnuException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(new ErrorDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(EmpruntInconnuException.class)
    public ResponseEntity<ErrorDTO> empruntInconnuException(EmpruntInconnuException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(new ErrorDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(DemandeInconnueException.class)
    public ResponseEntity<ErrorDTO> demandeInconnueException(DemandeInconnueException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(new ErrorDTO(e.getMessage(), status.value()));
    }

    // IMPOSSIBLE =======

    @ExceptionHandler(RetourImpossibleException.class)
    public ResponseEntity<ErrorDTO> retourImpossibleException(RetourImpossibleException e) {
        HttpStatus status = HttpStatus.CONFLICT;
        return ResponseEntity.status(status).body(new ErrorDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(LivrePlusEnStockException.class)
    public ResponseEntity<ErrorDTO> livrePlusEnStockException(LivrePlusEnStockException e) {
        HttpStatus status = HttpStatus.CONFLICT;
        return ResponseEntity.status(status).body(new ErrorDTO(e.getMessage(), status.value()));
    }


}
