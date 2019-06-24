
CREATE TABLE `users` (
  `id` int(10) UNSIGNED NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `date` date NOT NULL
)

INSERT INTO `users` (`id`, `username`, `password`, `date`) VALUES
(1, 'root', 'root', '2020-12-28');

ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

COMMIT;
