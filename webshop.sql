CREATE DATABASE webshop;

DROP TABLE IF EXISTS `webshop`.`items`;
DROP TABLE IF EXISTS `webshop`.`login`;
DROP TABLE IF EXISTS `webshop`.`myaccount`;

CREATE TABLE `webshop`.`items` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `imageurl` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `webshop`.`login` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `webshop`.`myaccount` (
  `email` VARCHAR(45) NOT NULL,
  `items` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`email`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `webshop`.`items` (`name`, `description`, `imageurl`) VALUES ('Batman v Superman: Dawn of Justice', 'Fearing that the actions of Superman are left unchecked, Batman takes on the Man of Steel, while the world wrestles with what kind of a hero it really needs.', 'images/1.jpg');
INSERT INTO `webshop`.`items` (`name`, `description`, `imageurl`) VALUES ('Captain America: Civil War', 'Political interference in the Avengers\' activities causes a rift between former allies Captain America and Iron Man.', 'images/2.jpg');
INSERT INTO `webshop`.`items` (`name`, `description`, `imageurl`) VALUES ('Doctor Strange', 'While on a journey of physical and spiritual healing, a brilliant neurosurgeon is drawn into the world of the mystic arts.', 'images/3.jpg');
INSERT INTO `webshop`.`items` (`name`, `description`, `imageurl`) VALUES ('Guardians of the Galaxy Vol. 2 ', 'Set to the backdrop of Awesome Mixtape #2, \'Guardians of the Galaxy Vol. 2\' continues the team\'s adventures as they unravel the mystery of Peter Quill\'s true parentage.', 'images/4.jpg');
INSERT INTO `webshop`.`items` (`name`, `description`, `imageurl`) VALUES ('Spiderman: Homecoming', 'Following the events of Captain America: Civil War (2016), Peter Parker attempts to balance his life in high school with his career as the web-slinging superhero Spider-Man.', 'images/5.jpg');
INSERT INTO `webshop`.`items` (`name`, `description`, `imageurl`) VALUES ('Suicide Squad', 'A secret government agency recruits some of the most dangerous incarcerated super-villains to form a defensive task force. Their first mission: save the world from the apocalypse.', 'images/6.jpg');
INSERT INTO `webshop`.`items` (`name`, `description`, `imageurl`) VALUES ('Rogue One: A Star Wars Story', 'The Rebel Alliance makes a risky move to steal the plans for the Death Star, setting up the epic saga to follow.', 'images/7.jpg');
INSERT INTO `webshop`.`items` (`name`, `description`, `imageurl`) VALUES ('Split', 'Three girls are kidnapped by a man with a diagnosed 23 distinct personalities. They must try to escape before the apparent emergence of a frightful new 24th.', 'images/8.jpg');
INSERT INTO `webshop`.`items` (`name`, `description`, `imageurl`) VALUES ('Assassin\'s Creed', 'When Callum Lynch explores the memories of his ancestor Aguilar and gains the skills of a Master Assassin, he discovers he is a descendant of the secret Assassins society.', 'images/9.jpg');
INSERT INTO `webshop`.`items` (`name`, `description`, `imageurl`) VALUES ('X-Men: Apocalypse', 'After the re-emergence of the world\'s first mutant, world-destroyer Apocalypse, the X-Men must unite to defeat his extinction level plan.', 'images/10.jpg');
INSERT INTO `webshop`.`items` (`name`, `description`, `imageurl`) VALUES ('Deadpool', 'A fast-talking mercenary with a morbid sense of humor is subjected to a rogue experiment that leaves him with accelerated healing powers and a quest for revenge.', 'images/11.jpg');
INSERT INTO `webshop`.`items` (`name`, `description`, `imageurl`) VALUES ('Star Trek Beyond', 'The USS Enterprise crew explores the furthest reaches of uncharted space, where they encounter a new ruthless enemy who puts them and everything the Federation stands for to the test.', 'images/12.jpg');
INSERT INTO `webshop`.`items` (`name`, `description`, `imageurl`) VALUES ('Jason Bourne', 'The CIA\'s most dangerous former operative is drawn out of hiding to uncover more explosive truths about his past.', 'images/13.jpg');
INSERT INTO `webshop`.`items` (`name`, `description`, `imageurl`) VALUES ('10 Cloverfield Lane', 'After getting in a car accident, a woman is held in a shelter with two men, who claim the outside world is affected by a widespread chemical attack.', 'images/14.jpg');
INSERT INTO `webshop`.`items` (`name`, `description`, `imageurl`) VALUES ('Alien: Covenant', 'The crew of a colony ship, bound for a remote planet, discover an uncharted paradise with a threat beyond their imagination, and must attempt a harrowing escape.', 'images/15.jpg');
INSERT INTO `webshop`.`items` (`name`, `description`, `imageurl`) VALUES ('Get Out', 'A young African-American man visits his Caucasian girlfriend\'s mysterious family estate.', 'images/16.jpg');
INSERT INTO `webshop`.`items` (`name`, `description`, `imageurl`) VALUES ('Baahubali 2: The Conclusion', 'When Shiva, the son of Bahubali, learns about his heritage, he begins to look for answers. His story is juxtaposed with past events that unfolded in the Mahishmati Kingdom.', 'images/17.jpg');
INSERT INTO `webshop`.`items` (`name`, `description`, `imageurl`) VALUES ('Logan', 'In the near future, a weary Logan cares for an ailing Professor X somewhere on the Mexican border. However, Logan\'s attempts to hide from the world and his legacy are upended when a young mutant arrives, pursued by dark forces.', 'images/18.jpg');
INSERT INTO `webshop`.`items` (`name`, `description`, `imageurl`) VALUES ('Wonder Woman', 'Before she was Wonder Woman she was Diana, princess of the Amazons, trained warrior. When a pilot crashes and tells of conflict in the outside world, she leaves home to fight a war to end all wars, discovering her full powers and true destiny.', 'images/19.jpg');
