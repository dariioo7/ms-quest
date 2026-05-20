CREATE TABLE quests (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        description TEXT NOT NULL,
                        quest_type VARCHAR(50) NOT NULL,
                        objective INT NOT NULL,
                        exp_reward INT NOT NULL,
                        coin_reward INT NOT NULL,
                        status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE'
);