package ba.unsa.etf.ppis_project.service;


import ba.unsa.etf.ppis_project.dto.UserDTO;
import ba.unsa.etf.ppis_project.model.User;
import ba.unsa.etf.ppis_project.repos.UserRepository;
import ba.unsa.etf.ppis_project.util.NotFoundException;
import org.springframework.data.domain.Sort;

import java.util.List;

@org.springframework.stereotype.Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAll() {
        final List<User> patients = userRepository.findAll(Sort.by("id"));
        return patients.stream()
                .map((user) -> mapToDTO(user, new UserDTO()))
                .toList();
    }

    private UserDTO mapToDTO(User user, UserDTO userDTO) {

        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    public UserDTO get(Integer userId) {

        return userRepository.findById(userId)
                .map(user -> mapToDTO(user, new UserDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public User getByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }
}
