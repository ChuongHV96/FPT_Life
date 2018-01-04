var express = require('express');
var router = express.Router();
var url = require('url');
var app = require('express')();
var bodyParser = require('body-parser');
var multer = require('multer'); // v1.0.5
var upload = multer(); // for parsing multipart/form-data
app.use(bodyParser.json()); // for parsing application/json
app.use(bodyParser.urlencoded({ extended: true })); // for parsing application/x-www-form-urlencoded


// var connection = mysql.createConnection({
// 	host: "us-cdbr-iron-east-05.cleardb.net",
// 	user: "b46b32fead3e5a",
// 	password: "6017cf3b",
// 	database: "heroku_8ab1d308346fa9a",
// 	timezone: 'utc'
//   });

//   connection.connect();


/* GET home page. */
router.get('/', function (req, res, next) {
	res.render('index', { title: 'FPT Life API' });
});


/* Get Student Login Service. */
router.post('/FPTLife/v1/login', upload.array(), function (req, res, next) {
	try {
		var username = req.body.username;
		var password = req.body.password;
		console.log(username);
		console.log(password);
		req.getConnection(function (err, conn) {
			if (err) {
				console.error('SQL Connection error: ', err);
				return next(err);
			} else {
				conn.query('select * from users where users.name = ? and users.password = ?', [username, password], function (err, rows, fields) {
					if (err) {
						console.error('SQL error: ', err);
						return next(err);
					}
					var resEmp = [];
					if (rows != 0) {
						for (var empIndex in rows) {
							var empObj = rows[empIndex];
							resEmp.push(empObj);
						}
						res.json(resEmp);
					} else {
						res.render('index', { title: 'No Records' });
					}
				});
			}
		});
	} catch (ex) {
		console.error("Internal error:" + ex);
		return next(ex);
	}
});

/* Get Student Info Service. */
router.post('/FPTLife/v1/getStudentInfo', upload.array(), function (req, res, next) {
	try {
		var username = req.body.username;
		var password = req.body.password;
		console.log(username);
		console.log(password);

		req.getConnection(function (err, conn) {
			if (err) {
				console.error('SQL Connection error: ', err);
				return next(err);
			} else {
				conn.query('select * from student where student.username = ? and student.password = ?', [username, password], function (err, rows, fields) {
					if (err) {
						console.error('SQL error: ', err);
						return next(err);
					}
					var resEmp = [];
					if (rows != 0) {
						for (var empIndex in rows) {
							var empObj = rows[empIndex];
							resEmp.push(empObj);
						}
						res.json(resEmp);
					} else {
						res.render('index', { title: 'No Records' });
					}
				});
			}
		});
	} catch (ex) {
		console.error("Internal error:" + ex);
		return next(ex);
	}
});


/* Get Ins Info Service. */
router.post('/FPTLife/v1/getInsInfo', upload.array(), function (req, res, next) {
	try {
		var username = req.body.username;
		var password = req.body.password;
		console.log(username);
		console.log(password);

		req.getConnection(function (err, conn) {
			if (err) {
				console.error('SQL Connection error: ', err);
				return next(err);
			} else {
				conn.query('select * from instructor where instructor.username = ? and instructor.password = ?', [username, password], function (err, rows, fields) {
					if (err) {
						console.error('SQL error: ', err);
						return next(err);
					}
					var resEmp = [];
					if (rows != 0) {
						for (var empIndex in rows) {
							var empObj = rows[empIndex];
							resEmp.push(empObj);
						}
						res.json(resEmp);
					} else {
						res.render('index', { title: 'No Records' });
					}
				});
			}
		});
	} catch (ex) {
		console.error("Internal error:" + ex);
		return next(ex);
	}
});

/* Get Student Schedule by Day Service. */
router.get('/FPTLife/v1/getStudentScheduleByDay', upload.array(), function (req, res, next) {
	try {
		/*var studentCode = req.body.studentCode;
		var date = req.body.date;*/
		var query = url.parse(req.url, true).query;
		var studentId = query.studentId;
		var date = query.date;
		req.getConnection(function (err, conn) {
			if (err) {
				console.error('SQL Connection error: ', err);
				return next(err);
			} else {
				conn.query('SELECT sb.subName, sb.subCode, sl.id, cl.classroomCode, ins.insName, ins.username, sh.date, cr.startDate, cr.endDate '
					+ 'FROM register re '
					+ 'INNER JOIN course as cr on re.course_id = cr.id '
					+ 'INNER JOIN subject as sb on cr.sub_id = sb.id '
					+ 'INNER JOIN schedule as sh on sh.course_id = cr.id '
					+ 'INNER JOIN instructor as ins on cr.ins_id = ins.id '
					+ 'INNER JOIN slot as sl on sl.id = sh.slot_id '
					+ 'INNER JOIN classroom as cl on cl.id = sh.classroom_id '
					+ 'WHERE re.stu_id = ? AND sh.date = ?'
					+ 'GROUP BY sb.subName, sb.subCode, sl.id, cl.classroomCode, ins.insName, ins.username, sh.date, cr.startDate, cr.endDate '
					+ 'ORDER BY sl.id', [studentId, date], function (err, rows, fields) {
						if (err) {
							console.error('SQL error: ', err);
							return next(err);
						}
						var resEmp = [];
						if (rows != 0) {
							for (var empIndex in rows) {
								var empObj = rows[empIndex];
								resEmp.push(empObj);
							}
							res.json(resEmp);
						} else {
							res.render('index', { title: 'No Records' });
						}
					});
			}
		});
	} catch (ex) {
		console.error("Internal error:" + ex);
		return next(ex);
	}
});

/* Get Student Schedule by Week Service. */
router.get('/FPTLife/v1/getStudentScheduleByWeek', upload.array(), function (req, res, next) {
	try {
		/*var studentCode = req.body.studentCode;
		var date = req.body.date;*/
		var query = url.parse(req.url, true).query;
		var studentId = query.studentId;
		var startDate = query.startDate;
		var endDate = query.endDate;
		req.getConnection(function (err, conn) {
			if (err) {
				console.error('SQL Connection error: ', err);
				return next(err);
			} else {
				conn.query('SELECT sb.subName, sb.subCode, sl.id, cl.classroomCode, ins.insName, ins.username, sh.date, cr.startDate, cr.endDate '
					+ 'FROM register re '
					+ 'INNER JOIN course as cr on re.course_id = cr.id '
					+ 'INNER JOIN subject as sb on cr.sub_id = sb.id '
					+ 'INNER JOIN schedule as sh on sh.course_id = cr.id '
					+ 'INNER JOIN instructor as ins on cr.ins_id = ins.id '
					+ 'INNER JOIN slot as sl on sl.id = sh.slot_id '
					+ 'INNER JOIN classroom as cl on cl.id = sh.classroom_id '
					+ 'WHERE re.stu_id = ? AND sh.date >= ? AND sh.date <= ?'
					+ 'GROUP BY sb.subName, sb.subCode, sl.id, cl.classroomCode, ins.insName, ins.username, sh.date, cr.startDate, cr.endDate '
					+ 'ORDER BY sh.date, sl.id', [studentId, startDate, endDate], function (err, rows, fields) {
						if (err) {
							console.error('SQL error: ', err);
							return next(err);
						}
						var resEmp = [];
						if (rows != 0) {
							for (var empIndex in rows) {
								var empObj = rows[empIndex];
								resEmp.push(empObj);
							}
							res.json(resEmp);
						} else {
							res.render('index', { title: 'No Records' });
						}
					});
			}
		});
	} catch (ex) {
		console.error("Internal error:" + ex);
		return next(ex);
	}
});


/* Get Teacher Schedule in day Service. */
router.get('/FPTLife/v1/getTeacherScheduleByDay', upload.array(), function (req, res, next) {
	try {
		/*var studentCode = req.body.studentCode;
		var date = req.body.date;*/
		var query = url.parse(req.url, true).query;
		var insId = query.insId;
		var date = query.date;
		req.getConnection(function (err, conn) {
			if (err) {
				console.error('SQL Connection error: ', err);
				return next(err);
			} else {
				conn.query('SELECT sb.subName, sb.subCode, sl.id, cl.classroomCode, ins.insName, ins.username, sh.date, cr.startDate, cr.endDate '
					+ 'FROM teaching tch '
					+ 'INNER JOIN instructor as ins on tch.ins_id = ins.id '
					+ 'INNER JOIN course as cr on cr.ins_id = tch.ins_id '
					+ 'INNER JOIN schedule as sh on sh.course_id = cr.id '
					+ 'INNER JOIN slot as sl on sl.id = sh.slot_id '
					+ 'INNER JOIN classroom as cl on cl.id = sh.classroom_id '
					+ 'INNER JOIN subject as sb on tch.sub_id = sb.id '
					+ 'WHERE tch.ins_id = ? AND sh.date = ? AND cr.sub_id = sb.id '
					+ 'GROUP BY sb.subName, sb.subCode, sl.id, cl.classroomCode, ins.insName, ins.username, sh.date, cr.startDate, cr.endDate '
					+ 'ORDER BY sl.id', [insId, date], function (err, rows, fields) {
						if (err) {
							console.error('SQL error: ', err);
							return next(err);
						}
						var resEmp = [];
						if (rows != 0) {
							for (var empIndex in rows) {
								var empObj = rows[empIndex];
								resEmp.push(empObj);
							}
							res.json(resEmp);
						} else {
							res.render('index', { title: 'No Records' });
						}
					});
			}
		});
	} catch (ex) {
		console.error("Internal error:" + ex);
		return next(ex);
	}
});

/* Get Teacher Schedule in week Service. */
router.get('/FPTLife/v1/getTeacherScheduleByWeek', upload.array(), function (req, res, next) {
	try {
		/*var studentCode = req.body.studentCode;
		var date = req.body.date;*/
		var query = url.parse(req.url, true).query;
		var insId = query.insId;
		var startDate = query.startDate;
		var endDate = query.endDate;
		req.getConnection(function (err, conn) {
			if (err) {
				console.error('SQL Connection error: ', err);
				return next(err);
			} else {
				conn.query('SELECT sb.subName, sb.subCode, sl.id, cl.classroomCode, ins.insName, ins.username, sh.date, cr.startDate, cr.endDate '
					+ 'FROM teaching tch '
					+ 'INNER JOIN instructor as ins on tch.ins_id = ins.id '
					+ 'INNER JOIN course as cr on cr.ins_id = tch.ins_id '
					+ 'INNER JOIN schedule as sh on sh.course_id = cr.id '
					+ 'INNER JOIN slot as sl on sl.id = sh.slot_id '
					+ 'INNER JOIN classroom as cl on cl.id = sh.classroom_id '
					+ 'INNER JOIN subject as sb on tch.sub_id = sb.id '
					+ 'WHERE tch.ins_id = ? AND sh.date >= ? AND sh.date <= ? AND cr.sub_id = sb.id '
					+ 'GROUP BY sb.subName, sb.subCode, sl.id, cl.classroomCode, ins.insName, ins.username, sh.date, cr.startDate, cr.endDate '
					+ 'ORDER BY sl.id', [insId, startDate, endDate], function (err, rows, fields) {
						if (err) {
							console.error('SQL error: ', err);
							return next(err);
						}
						var resEmp = [];
						if (rows != 0) {
							for (var empIndex in rows) {
								var empObj = rows[empIndex];
								resEmp.push(empObj);
							}
							res.json(resEmp);
						} else {
							res.render('index', { title: 'No Records' });
						}
					});
			}
		});
	} catch (ex) {
		console.error("Internal error:" + ex);
		return next(ex);
	}
});

/* Get Teacher Schedule in week Service. */
router.get('/FPTLife/v1/getAllTaskOfStudent', upload.array(), function (req, res, next) {
	try {
		/*var studentCode = req.body.studentCode;
		var date = req.body.date;*/
		var query = url.parse(req.url, true).query;
		var studentId = query.studentId;
		console.log(studentId);
		req.getConnection(function (err, conn) {
			if (err) {
				console.error('SQL Connection error: ', err);
				return next(err);
			} else {
				conn.query('SELECT task.id, task.stu_id, task.sub_id, task.taskType, sb.subCode, sb.subName, task.dueDate, task.title, task.detail '
					+ 'FROM task '
					+ 'INNER JOIN subject as sb on sb.id = task.sub_id '
					+ 'WHERE task.stu_id = ? '
					+ 'GROUP BY task.id, task.stu_id, task.sub_id, task.taskType, sb.subCode, sb.subName, task.dueDate, task.title, task.detail '
					, [studentId], function (err, rows, fields) {
						if (err) {
							console.error('SQL error: ', err);
							return next(err);
						}
						var resEmp = [];
						if (rows != 0) {
							for (var empIndex in rows) {
								var empObj = rows[empIndex];
								resEmp.push(empObj);
							}
							res.json(resEmp);
						} else {
							res.render('index', { title: 'No Records' });
						}
					});
			}
		});
	} catch (ex) {
		console.error("Internal error:" + ex);
		return next(ex);
	}
});

/* Get Teacher Schedule in week Service. */
router.get('/FPTLife/v1/getAllSubjectOfStudent', upload.array(), function (req, res, next) {
	try {
		/*var studentCode = req.body.studentCode;
		var date = req.body.date;*/
		var query = url.parse(req.url, true).query;
		var studentId = query.studentId;
		console.log(studentId);
		req.getConnection(function (err, conn) {
			if (err) {
				console.error('SQL Connection error: ', err);
				return next(err);
			} else {
				conn.query('SELECT sb.id, sb.subName, sb.subCode '
					+ 'FROM register re '
					+ 'INNER JOIN course as cr on cr.id = re.course_id '
					+ 'INNER JOIN subject as sb on sb.id = cr.sub_id '
					+ 'WHERE re.stu_id = ? '
					+ 'GROUP BY sb.id, sb.subName, sb.subCode'
					, [studentId], function (err, rows, fields) {
						if (err) {
							console.error('SQL error: ', err);
							return next(err);
						}
						var resEmp = [];
						if (rows != 0) {
							for (var empIndex in rows) {
								var empObj = rows[empIndex];
								resEmp.push(empObj);
							}
							res.json(resEmp);
						} else {
							res.render('index', { title: 'No Records' });
						}
					});
			}
		});
	} catch (ex) {
		console.error("Internal error:" + ex);
		return next(ex);
	}
});

/* Get Student Info Service. */
router.post('/FPTLife/v1/addTask', upload.array(), function (req, res, next) {
	try {
		var studentId = req.body.studentId;
		var subId = req.body.subId;
		var taskType = req.body.taskType;
		var dueDate = req.body.dueDate;
		var title = req.body.title;
		var detail = req.body.detail;

		req.getConnection(function (err, conn) {
			if (err) {
				console.error('SQL Connection error: ', err);
				return next(err);
			} else {
				conn.query('INSERT INTO task (stu_id, sub_id, taskType, dueDate, title, detail) VALUES (?, ?, ?, ?, ?, ?)'
					, [studentId, subId, taskType, dueDate, title, detail], function (err, rows, fields) {
						if (err) {
							res.send({ status: 0, message: 'TODO creation failed' });
							return next(err);
						} else {
							res.send({ status: 1, message: 'TODO created successfully' });
						}
					});
			}
		});
	} catch (ex) {
		console.error("Internal error:" + ex);
		return next(ex);
	}
});

module.exports = router;
