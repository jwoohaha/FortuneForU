-- --------------------------------------------------------
-- 호스트:                          i9a403.p.ssafy.io
-- 서버 버전:                        10.5.21-MariaDB-1:10.5.21+maria~ubu2004 - mariadb.org binary distribution
-- 서버 OS:                        debian-linux-gnu
-- HeidiSQL 버전:                  12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- 테이블 fortuneforu.counseling_reservation 구조 내보내기
CREATE TABLE IF NOT EXISTS `counseling_reservation` (
  `reservation_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `rez_report_status` varchar(255) DEFAULT NULL,
  `rez_time` datetime(6) DEFAULT NULL,
  `rez_recorded` varchar(255) DEFAULT NULL,
  `rez_report` varchar(255) DEFAULT NULL,
  `rez_review` varchar(255) DEFAULT NULL,
  `rez_score` decimal(2,1) DEFAULT NULL,
  `rez_status` varchar(255) DEFAULT NULL,
  `rez_url` varchar(255) DEFAULT NULL,
  `counselor_no` bigint(20) DEFAULT NULL,
  `member_no` bigint(20) DEFAULT NULL,
  `rez_type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`reservation_no`),
  KEY `FKlm81mx1powujuv1fakhtn8sys` (`counselor_no`),
  KEY `FKdpvnnki8onkj37pakqqk5wskf` (`member_no`),
  CONSTRAINT `FKdpvnnki8onkj37pakqqk5wskf` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`),
  CONSTRAINT `FKlm81mx1powujuv1fakhtn8sys` FOREIGN KEY (`counselor_no`) REFERENCES `counselor` (`counselor_no`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 fortuneforu.counseling_reservation:~32 rows (대략적) 내보내기
INSERT INTO `counseling_reservation` (`reservation_no`, `rez_report_status`, `rez_time`, `rez_recorded`, `rez_report`, `rez_review`, `rez_score`, `rez_status`, `rez_url`, `counselor_no`, `member_no`, `rez_type`) VALUES
	(12, 'COMPLETE', '2023-08-14 10:00:00.000000', 'url/record', 'url/report', '사주란 이거지!', 4.0, 'END', 'url/hello', 11, 27, 'SAJU'),
	(13, 'COMPLETE', '2023-08-16 11:00:00.000000', 'https://i9a403.p.ssafy.io:8443/openvidu/recordings/bitcoin2591547c96415-3a02-4955-ad00-751c0528c548/bitcoin2591547c96415-3a02-4955-ad00-751c0528c548.webm', '사주 상담을 위해서는 생년월일과 시간을 알려주셔야 합니다. 사주에 관련된 내용만 집중해서 말씀해주시면 제가 요약해드릴게요.', '사주란 이거지!', 4.0, 'END', NULL, 11, 27, 'SAJU'),
	(40, 'COMPLETE', '2023-08-16 19:30:00.000000', 'https://i9a403.p.ssafy.io:8443/openvidu/recordings/bitcoin2591576daf979-b3d2-4584-96a9-8f3939e9e3bb/bitcoin2591576daf979-b3d2-4584-96a9-8f3939e9e3bb.webm', 'test', NULL, NULL, 'END', NULL, 11, 52, 'TARO'),
	(41, 'NONE', '2023-08-17 14:30:00.000000', NULL, NULL, NULL, NULL, 'CANCEL', NULL, 12, 87, 'TARO'),
	(44, 'NONE', '2023-08-17 12:30:00.000000', NULL, NULL, NULL, NULL, 'CANCEL', NULL, 12, 52, 'TARO'),
	(45, 'COMPLETE', '2023-08-17 13:30:00.000000', NULL, 'test', NULL, NULL, 'END', NULL, 12, 52, 'TARO'),
	(46, 'COMPLETE', '2023-08-17 14:30:00.000000', NULL, 'test', '잘봐요', 5.0, 'END', NULL, 12, 52, 'TARO'),
	(47, 'COMPLETE', '2023-08-17 16:30:00.000000', NULL, 'test', '모르겠어요', 3.0, 'END', NULL, 12, 52, 'TARO'),
	(48, 'COMPLETE', '2023-08-17 15:30:00.000000', NULL, 'test', '못봐요', 1.0, 'END', NULL, 12, 52, 'TARO'),
	(49, 'COMPLETE', '2023-08-17 17:30:00.000000', NULL, NULL, NULL, NULL, 'CANCEL', NULL, 12, 52, 'TARO'),
	(50, 'COMPLETE', '2023-08-17 18:30:00.000000', NULL, NULL, NULL, NULL, 'CANCEL', NULL, 12, 52, 'TARO'),
	(51, 'COMPLETE', '2023-08-17 19:30:00.000000', NULL, NULL, NULL, NULL, 'CANCEL', NULL, 12, 52, 'TARO'),
	(53, 'COMPLETE', '2023-08-16 09:30:00.000000', 'https://i9a403.p.ssafy.io:8443/openvidu/recordings/mydowson29b809c2-473e-41e1-a889-b1beb7635926/mydowson29b809c2-473e-41e1-a889-b1beb7635926.webm', '상담 내용의 요약은 다음과 같습니다:\n- 컨벨트와 관련된 문제로 사주 상담이 필요합니다.\n- 한버트 한 것을 까먹은 것 같습니다.\n- 서버와 연결이 느려 배포 작업이 진행되고 있습니다.', NULL, NULL, 'END', NULL, 13, 51, 'TARO'),
	(56, 'COMPLETE', '2023-08-16 16:00:00.000000', 'https://i9a403.p.ssafy.io:8443/openvidu/recordings/mydowson27d0360d-c098-4a4e-aeba-521045df0120/mydowson27d0360d-c098-4a4e-aeba-521045df0120.webm', '상대방이 사주 상담가에게 사주 관련 질문을 하고 있습니다. 상대방은 이전에 사주를 삭제한 후 다시 복구하려다가 기억이 나지 않았다고 합니다. 또한, 상대방은 상남자2 종교에 대해 언급했습니다.', NULL, NULL, 'END', NULL, 13, 51, 'TARO'),
	(58, 'COMPLETE', '2023-08-17 19:00:00.000000', 'https://i9a403.p.ssafy.io:8443/openvidu/recordings/bitcoin2591514d465d5-28ac-44e4-99a9-0264d523d197/bitcoin2591514d465d5-28ac-44e4-99a9-0264d523d197.webm', '오지고 지리고 렛잇고', NULL, NULL, 'END', NULL, 11, 54, 'TARO'),
	(59, 'NONE', '2023-08-17 13:30:00.000000', NULL, NULL, NULL, NULL, 'CANCEL', NULL, 13, 89, 'TARO'),
	(60, 'COMPLETE', '2023-08-17 21:00:00.000000', 'https://i9a403.p.ssafy.io:8443/openvidu/recordings/bitcoin25915f3f04f39-04d2-4616-bc11-814c7eda22d9/bitcoin25915f3f04f39-04d2-4616-bc11-814c7eda22d9.webm', '사주 상담 요약: 상담가가 사주에 대한 내용을 말하지 않고, 사주 상담에 관련된 부분만 요약해줄 것을 요청하였음.', NULL, NULL, 'END', NULL, 11, 89, 'TARO'),
	(63, 'COMPLETE', '2023-08-14 20:00:00.000000', 'https://i9a403.p.ssafy.io:8443/openvidu/recordings/bitcoin2591595b41080-4d01-4d50-820f-a67a7e475e4f/bitcoin2591595b41080-4d01-4d50-820f-a67a7e475e4f.webm', '태어난 날짜와 연도를 알려주시면 사주에 관한 상담을 도와드릴 수 있습니다. 뒤에말 다 짤렸넹?', NULL, NULL, 'END', NULL, 11, 52, 'TARO'),
	(65, 'NONE', '2023-08-31 19:00:00.000000', NULL, NULL, NULL, NULL, 'WAITING', NULL, 11, 52, 'TARO'),
	(66, 'COMPLETE', '2023-08-17 20:30:00.000000', 'https://i9a403.p.ssafy.io:8443/openvidu/recordings/bitcoin259153a8c9f55-aa08-4ad9-88e1-cdab1d802535/bitcoin259153a8c9f55-aa08-4ad9-88e1-cdab1d802535.webm', '고민하고 있는 사람은 시험에서 실패한 후 자신을 어떻게 진정시킬지에 대해 고민하고 있습니다. 상담사로서 제안해볼 만한 것은 타로 카드를 이용해서 조언을 드리는 것입니다. 이 사람은 타로 카드를 펼치면서 자신을 안정시키기 위해 그 카드의 의미를 찾으려고 합니다.', NULL, NULL, 'END', NULL, 11, 52, 'TARO'),
	(68, 'NONE', '2023-08-19 12:30:00.000000', NULL, NULL, NULL, NULL, 'WAITING', NULL, 13, 51, 'SAJU'),
	(69, 'NONE', '2023-08-19 16:00:00.000000', NULL, NULL, NULL, NULL, 'WAITING', NULL, 4, 52, 'TARO'),
	(70, 'NONE', '2023-08-19 15:00:00.000000', NULL, NULL, NULL, NULL, 'WAITING', NULL, 4, 52, 'TARO'),
	(71, 'COMPLETE', '2023-08-17 20:00:00.000000', 'https://i9a403.p.ssafy.io:8443/openvidu/recordings/bitcoin25915bda628e6-b225-4eb0-ab91-70d3dcd018d2/bitcoin25915bda628e6-b225-4eb0-ab91-70d3dcd018d2.webm', '인생이란..', '오지고 지리고 렛잇고', 5.0, 'END', NULL, 11, 71, 'SAJU'),
	(73, 'NONE', '2023-08-17 14:00:00.000000', NULL, NULL, NULL, NULL, 'CANCEL', NULL, 6, 90, 'TARO'),
	(74, 'NONE', '2023-08-21 19:30:00.000000', NULL, NULL, NULL, NULL, 'WAITING', NULL, 11, 53, 'TARO'),
	(75, 'NONE', '2023-08-20 19:00:00.000000', NULL, NULL, NULL, NULL, 'WAITING', NULL, 11, 52, 'TARO'),
	(76, 'NONE', '2023-08-20 12:30:00.000000', NULL, NULL, NULL, NULL, 'WAITING', NULL, 13, 88, 'TARO'),
	(77, 'NONE', '2023-08-20 14:00:00.000000', NULL, NULL, NULL, NULL, 'WAITING', NULL, 13, 88, 'TARO'),
	(78, 'NONE', '2023-08-20 15:00:00.000000', NULL, NULL, NULL, NULL, 'WAITING', NULL, 13, 88, 'TARO'),
	(80, 'COMPLETE', '2023-08-18 12:00:00.000000', 'https://i9a403.p.ssafy.io:8443/openvidu/recordings/bitcoin259156c322de8-3341-490f-9006-40b178acdd8b/bitcoin259156c322de8-3341-490f-9006-40b178acdd8b.webm', '사주 상담을 통해 사용자가 자기 결혼에 대해 궁금한 상황으로 보입니다. 그러나, 상담 내용이 약간 혼동되어 이해하기 어렵습니다. 추가 정보를 제공해주시면 보다 정확하게 답변드릴 수 있습니다. 네??', NULL, NULL, 'END', NULL, 11, 88, 'TARO'),
	(81, 'COMPLETE', '2023-08-18 09:30:00.000000', 'https://i9a403.p.ssafy.io:8443/openvidu/recordings/jwoohaha3ad77b64-0a0e-4bd7-93fe-e46ce4ea72f3/jwoohaha3ad77b64-0a0e-4bd7-93fe-e46ce4ea72f3.webm', '사주 분석을 통해 본 이야기입니다.\n\n적당한 휴식과 재충전이 필요하며, 일관된 태도로 평중심을 유지한다면 좋은 결과가 있을 것이라고 말하였습니다. \n또한, 구체적으로 휴식을 취하는 것도 도움이 될 것이라고 언급하였습니다. \n하지만 마지막 문장은 어떤 사람의 대화에 해당하는지 알 수 없다고 말하였습니다.', '좋은 상담이었습니다?', 4.0, 'END', NULL, 12, 53, 'TARO');

-- 테이블 fortuneforu.counselor 구조 내보내기
CREATE TABLE IF NOT EXISTS `counselor` (
  `counselor_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `career` varchar(255) DEFAULT NULL,
  `counselor_type` varchar(255) DEFAULT NULL,
  `end_time` varchar(255) DEFAULT NULL,
  `intro` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `rating_avg` float NOT NULL DEFAULT 0,
  `review_cnt` int(11) NOT NULL DEFAULT 0,
  `start_time` varchar(255) DEFAULT NULL,
  `member_no` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`counselor_no`),
  KEY `FKs7mumpkxxku1pglfvhlrurrmo` (`member_no`),
  CONSTRAINT `FKs7mumpkxxku1pglfvhlrurrmo` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 fortuneforu.counselor:~13 rows (대략적) 내보내기
INSERT INTO `counselor` (`counselor_no`, `address`, `career`, `counselor_type`, `end_time`, `intro`, `major`, `phone`, `rating_avg`, `review_cnt`, `start_time`, `member_no`) VALUES
	(4, '123 Main St City', '5Years', 'TARO', '16:00', '세상에 우연은 없습니다', '연애', '010-1234-5678', 0, 0, '13:00', 27),
	(5, 'hello St City', '6years', 'TARO', '16:30', '누구나 다 정자였어 어쩌면 우리는 모두 박정자가 아닐까?', '재물', '010-7777-7777', 0, 0, '09:00', 28),
	(6, 'Bye St City', '2 years', 'BOTH', '17:00', '하늘의 소리를 전합니다', '건강', '010-2222-2222', 0, 0, '11:00', 30),
	(7, '안녕하세요 St City', '5 years', 'SAJU', '18:00', '혼자서 고민하지 마세요', '사랑', '010-6666-6666', 0, 0, '14:00', 31),
	(8, '안녕히가세요 City', '4 years', 'TARO', '19:00', '족집게 같은 상담', '연애', '010-8888-8888', 0, 0, '15:00', 32),
	(9, 'Heaven', '3 years', 'BOTH', '20:00', '공감과 힐링을 바탕으로 상담해 드립니다', '재물', '010-4444-4444', 0, 0, '16:00', 33),
	(10, 'Hell', '2 years', 'SAJU', '21:00', '어느 누구에게나 삶은 힘듭니다', '건강', '010-1111-1111', 0, 0, '17:00', 34),
	(11, 'Hell', '2 years', 'BOTH', '21:00', '인생은 진인사 대천명', '연애', '010-1111-1111', 3.4, 5, '12:00', 51),
	(12, 'ssafy City', '26 years', 'BOTH', '18:00', '말 못할 고민으로 힘들고 지칠 때 저에게 오세요', '사랑', '010-1234-3210', 3.25, 4, '09:00', 71),
	(13, 'ssafy City', '26 yearss', 'BOTH', '18:00', '불확실한 미래와 선택의 갈림길에서 고민하신다면 저에게', '재물', '010-1234-3218', 2.33333, 3, '09:00', 52),
	(14, 'ssafy City', '2 years', 'SAJU', '18:00', '불확실한 미래와 선택의 갈림길에서 고민하신다면 저에게', '사랑', '010-1234-3218', 0, 0, '09:00', 35),
	(15, 'ssafy City', '3 years', 'TARO', '18:00', '불확실한 미래와 선택의 갈림길에서 고민하신다면 저에게', '건강', '010-1234-3218', 0, 0, '09:00', 36),
	(18, '광주광역시 서구', 'MBTI 석사 과정', 'SAJU', '18:00', '방황을 하고 있다면 저를 찾아오세요...', '직장/진로', '010-0000-0000', 0, 0, '09:00', 54);

-- 테이블 fortuneforu.counselor_form 구조 내보내기
CREATE TABLE IF NOT EXISTS `counselor_form` (
  `conselor_form_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `career` varchar(255) DEFAULT NULL,
  `counselor_type` varchar(255) DEFAULT NULL,
  `intro` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `pass_state` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `member_no` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`conselor_form_no`),
  KEY `FKbgl0gm901cag6qkeqdknjja3s` (`member_no`),
  CONSTRAINT `FKbgl0gm901cag6qkeqdknjja3s` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 fortuneforu.counselor_form:~25 rows (대략적) 내보내기
INSERT INTO `counselor_form` (`conselor_form_no`, `created`, `last_updated`, `address`, `career`, `counselor_type`, `intro`, `major`, `pass_state`, `phone`, `reason`, `member_no`) VALUES
	(1, '2023-08-16 00:55:43', '2023-08-16 00:55:43', 'address0', 'career0', 'SAJU', 'intro0', 'major0', 'PASS', 'phone0', NULL, 46),
	(2, '2023-08-16 00:55:43', '2023-08-16 00:55:43', 'address1', 'career1', 'TARO', 'intro1', 'major1', 'WAITING', 'phone1', NULL, 47),
	(3, '2023-08-16 00:55:43', '2023-08-16 00:55:43', 'address2', 'career2', 'BOTH', 'intro2', 'major2', 'REJECT', 'phone2', NULL, 48),
	(4, '2023-08-16 00:55:43', '2023-08-16 00:55:43', 'address3', 'career3', 'SAJU', 'intro3', 'major3', 'PASS', 'phone3', NULL, 46),
	(5, '2023-08-16 00:55:43', '2023-08-16 00:55:43', 'address4', 'career4', 'TARO', 'intro4', 'major4', 'WAITING', 'phone4', NULL, 47),
	(6, '2023-08-16 00:55:43', '2023-08-16 00:55:43', 'address5', 'career5', 'BOTH', 'intro5', 'major5', 'REJECT', 'phone5', NULL, 48),
	(7, '2023-08-16 00:55:43', '2023-08-16 00:55:43', 'address6', 'career6', 'SAJU', 'intro6', 'major6', 'PASS', 'phone6', NULL, 46),
	(8, '2023-08-16 00:55:43', '2023-08-16 00:55:43', 'address7', 'career7', 'TARO', 'intro7', 'major7', 'WAITING', 'phone7', NULL, 47),
	(9, '2023-08-16 00:55:43', '2023-08-16 00:55:43', 'address8', 'career8', 'BOTH', 'intro8', 'major8', 'REJECT', 'phone8', NULL, 48),
	(10, '2023-08-16 00:55:43', '2023-08-16 00:55:43', 'address9', 'career9', 'SAJU', 'intro9', 'major9', 'PASS', 'phone9', NULL, 46),
	(11, '2023-08-16 00:55:43', '2023-08-16 00:55:43', 'address10', 'career10', 'TARO', 'intro10', 'major10', 'WAITING', 'phone10', NULL, 47),
	(12, '2023-08-16 00:55:43', '2023-08-16 00:55:43', 'address11', 'career11', 'BOTH', 'intro11', 'major11', 'REJECT', 'phone11', NULL, 48),
	(13, '2023-08-16 00:55:43', '2023-08-16 00:55:43', 'address12', 'career12', 'SAJU', 'intro12', 'major12', 'PASS', 'phone12', NULL, 46),
	(14, '2023-08-16 00:55:43', '2023-08-16 00:55:43', 'address13', 'career13', 'TARO', 'intro13', 'major13', 'WAITING', 'phone13', NULL, 47),
	(15, '2023-08-16 00:55:44', '2023-08-16 00:55:44', 'address14', 'career14', 'BOTH', 'intro14', 'major14', 'REJECT', 'phone14', NULL, 48),
	(16, '2023-08-16 00:55:44', '2023-08-16 00:55:44', 'address15', 'career15', 'SAJU', 'intro15', 'major15', 'PASS', 'phone15', NULL, 46),
	(17, '2023-08-16 00:55:44', '2023-08-16 00:55:44', 'address16', 'career16', 'TARO', 'intro16', 'major16', 'WAITING', 'phone16', NULL, 47),
	(18, '2023-08-16 00:55:44', '2023-08-16 03:51:59', 'address17', 'career17', 'BOTH', 'intro17', 'major17', 'REJECT', 'phone17', '', 48),
	(19, '2023-08-16 11:32:51', '2023-08-17 16:09:39', 'afds', 'asfd', 'SAJU', 'adfs', '작명/개명', 'REJECT', 'adsf', '자기소개가 엉망입니다 다시 작성해주세요', 45),
	(20, '2023-08-16 02:39:03', '2023-08-16 03:49:07', 'asfd', 'adfs', 'SAJU', 'sadf', '미래/해몽', 'PASS', 'sadf', '사유', 54),
	(21, '2023-08-16 02:39:39', '2023-08-16 03:51:52', '주소', '없음', 'BOTH', '소개', '직장/진로 시험/진학 시험/진학', 'PASS', '전화번호', '', 52),
	(23, '2023-08-16 03:53:13', '2023-08-16 05:20:21', '역삼역', '신입', 'BOTH', '신입 사주 역술가', '연애/결혼 연애/결혼 연애/결혼', 'PASS', '112', '', 87),
	(25, '2023-08-17 01:37:04', '2023-08-17 09:47:43', '경기도 안양시', '운전면허증이 있습니다.\n\nISTQB TTA자격증이 있습니다.', 'TARO', '저는 테스트 입니다.', '직장/진로', 'REJECT', '031)4654-2345', NULL, 89),
	(27, '2023-08-17 18:49:51', '2023-08-17 18:56:01', 'asdr', 'asdf', 'SAJU', 'adsf', '가족/건강', 'PASS', 'sadf', NULL, 54),
	(28, '2023-08-17 13:20:26', '2023-08-17 16:09:20', '광주광역시 서구', 'MBTI 석사 과정', 'SAJU', '방황을 하고 있다면 저를 찾아오세요...', '직장/진로', 'PASS', '010-0000-0000', '알맞은 인재입니다', 54);

-- 테이블 fortuneforu.follow 구조 내보내기
CREATE TABLE IF NOT EXISTS `follow` (
  `created` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `follower_id` bigint(20) NOT NULL,
  `followee_id` bigint(20) NOT NULL,
  PRIMARY KEY (`followee_id`,`follower_id`),
  KEY `FKtps7gpodlrhxlji90u6r3mlng` (`follower_id`),
  CONSTRAINT `FK9t49cux0sxqh5usjf96sm03uv` FOREIGN KEY (`followee_id`) REFERENCES `member` (`member_no`),
  CONSTRAINT `FKtps7gpodlrhxlji90u6r3mlng` FOREIGN KEY (`follower_id`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 fortuneforu.follow:~12 rows (대략적) 내보내기
INSERT INTO `follow` (`created`, `last_updated`, `follower_id`, `followee_id`) VALUES
	('2023-08-17 05:04:30', '2023-08-17 05:04:30', 52, 27),
	('2023-08-17 13:55:48', '2023-08-17 13:55:48', 71, 27),
	('2023-08-17 14:04:28', '2023-08-17 14:04:28', 53, 30),
	('2023-08-17 08:53:29', '2023-08-17 08:53:29', 51, 51),
	('2023-08-17 14:04:33', '2023-08-17 14:04:33', 53, 51),
	('2023-08-17 13:58:09', '2023-08-17 13:58:09', 71, 51),
	('2023-08-18 00:21:36', '2023-08-18 00:21:36', 88, 51),
	('2023-08-17 13:58:12', '2023-08-17 13:58:12', 71, 52),
	('2023-08-17 05:07:00', '2023-08-17 05:07:00', 90, 52),
	('2023-08-17 05:04:35', '2023-08-17 05:04:35', 52, 71),
	('2023-08-18 00:23:50', '2023-08-18 00:23:50', 53, 71),
	('2023-08-17 16:05:59', '2023-08-17 16:05:59', 54, 71);

-- 테이블 fortuneforu.member 구조 내보내기
CREATE TABLE IF NOT EXISTS `member` (
  `member_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'1',
  `name` varchar(255) DEFAULT NULL,
  `account_id` varchar(255) DEFAULT NULL,
  `auth_provider` varchar(255) DEFAULT NULL,
  `profile_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`member_no`),
  UNIQUE KEY `UK_9esvgikrmti1v7ci6v453imdc` (`name`),
  UNIQUE KEY `UK_i54h1gvvnejys85e9d9qo9f2u` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 fortuneforu.member:~34 rows (대략적) 내보내기
INSERT INTO `member` (`member_no`, `email`, `is_active`, `name`, `account_id`, `auth_provider`, `profile_image`) VALUES
	(27, 'user1@gmail.com', b'1', '김꽃순', '1', 'GOOGLE', NULL),
	(28, 'user2@gmail.com', b'1', '박정자', '2', 'GOOGLE', NULL),
	(30, 'user3@gmail.com', b'1', '김병만', '3', 'GOOGLE', NULL),
	(31, 'user4@gmail.com', b'1', '유정인', '4', 'GOOGLE', NULL),
	(32, 'user5@gmail.com', b'1', '정민기', '5', 'GOOGLE', NULL),
	(33, 'user6@gmail.com', b'1', '설희', '6', 'GOOGLE', NULL),
	(34, 'user7@gmail.com', b'1', '아이리스', '7', 'GOOGLE', NULL),
	(35, 'user8@gmail.com', b'1', '우주보살', '8', 'GOOGLE', NULL),
	(36, 'user9@gmail.com', b'1', '사주플랜', '9', 'GOOGLE', NULL),
	(37, 'user10@gmail.com', b'1', '일천신당', '10', 'GOOGLE', NULL),
	(38, 'user11@gmail.com', b'1', '다희철학', '11', 'KAKAO', NULL),
	(39, 'user12@gmail.com', b'1', '천기누설', '12', 'KAKAO', NULL),
	(40, 'user13@gmail.com', b'1', '오로라', '13', 'KAKAO', NULL),
	(41, 'user14@gmail.com', b'1', '효금당', '14', 'KAKAO', NULL),
	(42, 'user15@gmail.com', b'1', '사주명당', '15', 'KAKAO', NULL),
	(43, 'user16@gmail.com', b'1', '만수사', '16', 'KAKAO', NULL),
	(44, 'user17@gmail.com', b'1', '하나', '17', 'KAKAO', NULL),
	(45, 'user18@gmail.com', b'1', '두리', '18', 'KAKAO', NULL),
	(46, 'user19@gmail.com', b'1', '청산', '19', 'KAKAO', NULL),
	(47, 'user20@gmail.com', b'1', '마음상담', '20', 'KAKAO', NULL),
	(48, 'user21@gmail.com', b'1', '유주', '21', 'KAKAO', NULL),
	(49, 'user22@gmail.com', b'1', '윤아', '22', 'KAKAO', NULL),
	(50, 'user23@gmail.com', b'1', '김도령', '23', 'KAKAO', NULL),
	(51, 'bitcoin25915@gmail.com', b'1', '김서우', '113678503795191004382', 'GOOGLE', '/opt/images/1692264498603_iikmung.png'),
	(52, 'mydowson@gmail.com', b'1', '정지원', '101671131194112182851', 'GOOGLE', '/opt/images/1692265042991_능이버섯.jpg'),
	(53, 'zlagg23@gmail.com', b'1', 'HyeJin Kim', '107057493382478284026', 'GOOGLE', '/opt/images/1692287165159_profile_default_img.png'),
	(54, 'soun997@gmail.com', b'1', '박소윤', '103383187608363790407', 'GOOGLE', '/opt/images/1692171025264_gun.gif'),
	(71, 'jwoohaha@gmail.com', b'1', '하재우', '104577004781616899503', 'GOOGLE', '/opt/images/1692318208135_다운로드.jfif'),
	(86, 'gogobattle@naver.com', b'1', '김승욱', '2967071315', 'KAKAO', NULL),
	(87, 'ssafycoach03@gmail.com', b'1', 'Coach', '111048924744723461741', 'GOOGLE', NULL),
	(88, 'gogobattle@gmail.com', b'1', '김싸피', '103649506340951006446', 'GOOGLE', '/opt/images/1692318447006_익명이.jfif'),
	(89, 'cheuora@gmail.com', b'1', 'Kim Sungjoon', '117282990792345287651', 'GOOGLE', NULL),
	(90, 'kiyoog02@gmail.com', b'1', '[서울_4반_A403_강인영]', '117443090239275032418', 'GOOGLE', NULL),
	(91, 'bljh1008@naver.com', b'1', '김혜림', '2971786600', 'KAKAO', NULL);

-- 테이블 fortuneforu.member_role 구조 내보내기
CREATE TABLE IF NOT EXISTS `member_role` (
  `member_no` bigint(20) NOT NULL,
  `roles` varchar(255) DEFAULT NULL,
  KEY `FKr4x8609l32oouja8cpn0rujhp` (`member_no`),
  CONSTRAINT `FKr4x8609l32oouja8cpn0rujhp` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 fortuneforu.member_role:~35 rows (대략적) 내보내기
INSERT INTO `member_role` (`member_no`, `roles`) VALUES
	(28, 'ROLE_COUNSELOR'),
	(30, 'ROLE_COUNSELOR'),
	(31, 'ROLE_COUNSELOR'),
	(32, 'ROLE_COUNSELOR'),
	(33, 'ROLE_COUNSELOR'),
	(34, 'ROLE_COUNSELOR'),
	(35, 'ROLE_COUNSELOR'),
	(36, 'ROLE_COUNSELOR'),
	(37, 'ROLE_USER'),
	(38, 'ROLE_USER'),
	(39, 'ROLE_USER'),
	(40, 'ROLE_USER'),
	(41, 'ROLE_USER'),
	(42, 'ROLE_USER'),
	(43, 'ROLE_USER'),
	(44, 'ROLE_USER'),
	(45, 'ROLE_USER'),
	(46, 'ROLE_USER'),
	(47, 'ROLE_USER'),
	(48, 'ROLE_USER'),
	(49, 'ROLE_USER'),
	(50, 'ROLE_USER'),
	(51, 'ROLE_COUNSELOR'),
	(53, 'ROLE_USER'),
	(71, 'ROLE_COUNSELOR'),
	(86, 'ROLE_USER'),
	(87, 'ROLE_USER'),
	(88, 'ROLE_USER'),
	(89, 'ROLE_USER'),
	(90, 'ROLE_USER'),
	(91, 'ROLE_USER'),
	(52, 'ROLE_ADMIN'),
	(27, 'ROLE_COUNSELOR'),
	(54, 'ROLE_USER'),
	(54, 'ROLE_COUNSELOR');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
