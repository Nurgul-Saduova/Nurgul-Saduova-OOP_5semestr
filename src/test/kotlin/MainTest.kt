import model.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MainTest {

    @BeforeAll
    fun init() {
        mapOf(
            "Lecture" to "Lec",
            "Laboratory" to "Lab",
            "Test" to "Tst"
        ).forEach {
            taskTypes.add(Type(it.key, it.value))
        }
        mapOf(
            "Sheldon" to "Professor",
            "Leonard" to "Professor"
        ).forEach {
            persons.add(Tutor(it.key, it.value))
        }
        mapOf(
            "Howard" to "Footprint on the Moon",
            "Raj" to "Footprint on the Moon",
            "Penny" to "Waitress"
        ).forEach {
            persons.add(Student(it.key, it.value))
        }
        listOf(
            "Math",
            "Phis",
            "History"
        ).forEach {
            courses.add(Course(it))
        }
        courses["Math"]?.run {
            addTutorByName("Sheldon")
            addStudentByName("Howard")
            addStudentByName("Penny")
            tasks.add(Task("Intro", taskTypes["Lecture"]!!,maxValue = 1))
            tasks.add(Task("UML", taskTypes["Lecture"]!!,maxValue = 1))
            tasks.add(Task("Uml lab", taskTypes["Laboratory"]!!, maxValue = 5))
        }
    }

    @Test
    fun initTest() {
        assertEquals(2, persons.all().filterIsInstance<Tutor>().size)
        assertEquals(3, persons.all().filterIsInstance<Student>().size)
        assertEquals(2, persons.all().filter {
            when (it) {
                is Student -> it.group == "Footprint on the Moon"
                else -> false
            }
        }.size)
        assertEquals(2, courses["Math"]?.students?.size)
        assertEquals(1, courses["Math"]?.tutors?.size)
    }

    @Test
    fun setGradeTest() {
        val math = courses["Math"] ?: fail()
        math.setGrade("UML", "Howard", 1)
        assertEquals(
            1,
            math.tasks.find { it.name == "UML" }?.grades?.size
        )
    }

    @Test
    fun studentGradesTest() {
        val math = courses["Math"] ?: fail()
        math.setGrade("Intro", "Penny", 1)
        math.setGrade("Uml lab", "Penny", 3)
        math.setGrade("Uml lab", "Penny", 4)
        val grades = math.studentGrades("Penny")
        assertEquals(1, grades["Intro"])
        assertEquals(4, grades["Uml lab"])
    }

    @Test
    fun setTaskTest(){
        val history = courses["History"]?: fail()
        history.setTask("XX",taskTypes["Test"]!!,"",5)
        assertEquals(
            5,
            history.tasks.find{it.name == "XX"}?.maxValue
        )
    }

    @Test
    fun addStudentsTest(){
        val students = arrayListOf(Student("Julia","28z"), Student("Nurgul","28z"), Student("Dzanna","28z") ,Student("Anastasia", "28z"))
        val math = courses["Math"] ?: fail()
        math.addStudents(students)
        assertEquals(
            "Julia",
            math.students.find{it.name == "Julia"}?.name
        )
        assertEquals(
            "28z",
            math.students.find{it.name == "Nurgul"}?.group
        )
    }

    @Test
    fun checkRatingTest(){
        val weights = mapOf<Task,Int>(
            Task("lec1",Type("Lecture","Lec"),"",1) to 5,
            Task("lab1", Type("Laboratory","Lab"),"", 5) to 10,
            Task("tst1", Type("Test","Tst"),"",10)to 20)
        val math = courses["Math"] ?: fail()
       /* val students = arrayListOf(Student("Julia","28z"))
        math.addStudents(students)*/
        math.setGrade("lec1", "Penny", 1)
        math.setGrade("lab1", "Penny", 5)
        math.setGrade("lab1", "Penny", 0)
        math.setGrade("tst1","Penny",10)
        math.setGrade("tst1","Penny",0)
        math.checkRating(weights)
        assertEquals(
            35,
             math.ratings["Penny"]?.value
        )
    }
}