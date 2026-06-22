package com.info.spring.dar.springboot_aplicacion.service;

// Indica que esta clase pertenece a la capa de servicios
import org.springframework.stereotype.Service;

// Permite trabajar con listas
import java.util.List;

// Importa las entidades necesarias
import com.info.spring.dar.springboot_aplicacion.entity.Profile;
import com.info.spring.dar.springboot_aplicacion.entity.User;

// Importa el repositorio
import com.info.spring.dar.springboot_aplicacion.repository.ProfileRepository;

/*
 * Esta clase contiene la lógica relacionada con los perfiles.
 *
 * Se comunica con ProfileRepository para acceder a la base de datos.
 */
@Service
public class ProfileService {

    // Objeto que permite acceder a la tabla profiles
    private final ProfileRepository profileRepository;

    /*
     * Constructor con inyección de dependencias.
     */
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    /*
     * Guarda un perfil en la base de datos.
     */
    public Profile saveProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    /*
     * Devuelve todos los perfiles registrados.
     */
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    /*
     * Busca un perfil por su id.
     *
     * Si no existe, devuelve null.
     */
    public Profile getProfileById(Long id) {
        return profileRepository.findById(id).orElse(null);
    }

    /*
     * Devuelve todos los perfiles
     * pertenecientes a un usuario.
     */
    public List<Profile> getProfilesByUser(User user) {
        return profileRepository.findByUser(user);
    }

    /*
     * Busca perfiles por nombre.
     */
    public List<Profile> getProfilesByFullName(String fullName) {
        return profileRepository.findByFullName(fullName);
    }

    /*
     * Busca un perfil por correo.
     */
    public Profile getProfileByEmail(String email) {
        return profileRepository.findByEmail(email);
    }

    /*
     * Elimina un perfil por su id.
     */
    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }

}
