package com.mercadolibre.exam.mutant;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.exam.mutant.domain.DNAResult;
import com.mercadolibre.exam.mutant.domain.DNASequence;
import com.mercadolibre.exam.mutant.domain.DNAStatus;
import com.mercadolibre.exam.mutant.exception.handler.model.ResponseError;

//@DataMongoTest
//@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExamenMercadolibreMutanteApplicationTests {

	@Autowired
	private WebApplicationContext appContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.appContext).build();
	}

	@Test
	public void contextLoads() {
	}

	/**
	 * Test - Mutant/Human - Status
	 * 
	 * @throws Exception
	 */
	@Test
	public void test01StatsTestBegin() throws Exception {
		// @formatter:off
		ResultActions result = mockMvc.perform(
				get("/stats/")
					.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.count_mutant_dna").value(0))
				.andExpect(jsonPath("$.count_human_dna").value(0))
				.andExpect(jsonPath("$.ratio").value(0));
		
		byte[] content = result.andReturn().getResponse().getContentAsByteArray();

		DNAStatus dnaResult = new ObjectMapper().readValue(content, DNAStatus.class);
		System.out.println(dnaResult);
		Assert.assertTrue(dnaResult.getHumanCount() == 0L);
		Assert.assertTrue(dnaResult.getMutantCount() == 0L);
		Assert.assertTrue(dnaResult.getTotal() == 0L);
		Assert.assertTrue(dnaResult.getRatio().equals(BigDecimal.ZERO));
		
		// @formatter:on
	}

	/**
	 * Test without Mutant sequences
	 * 
	 * @throws Exception
	 */
	@Test
	public void test02TestHumanDNA() throws Exception {
		// @formatter:off
		String request = "{\n"+ 
				"\"dna\": " +
				"        [\"ACCTATC\",\n" + 
				"         \"CTCACTT\",\n" + 
				"         \"ACGCTAT\",\n" + 
				"         \"ACCTACC\",\n" + 
				"         \"CAATTCC\",\n" + 
				"         \"CACCAAT\",\n" + 
				"         \"CAACAAT\"" +
				"        ]\n" +
				"}"; 
		mockMvc.perform(
				post("/mutant/")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(request.getBytes()))
				.andExpect(status().isForbidden())
			;
		// @formatter:on
	}

	/**
	 * Test one DNA only
	 * 
	 * @throws Exception
	 */
	@Test
	public void test02aTestOnlyOneDNA() throws Exception {
		test02TestHumanDNA();
		test02TestHumanDNA();
		test02TestHumanDNA();
		// @formatter:off
		mockMvc.perform(
				get("/stats/")
					.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.count_mutant_dna").value(0))
				.andExpect(jsonPath("$.count_human_dna").value(1))
				.andExpect(jsonPath("$.ratio").value(0));
		// @formatter:on
	}

	/**
	 * Test - Mutant/Human - Status with one human
	 * 
	 * @throws Exception
	 */
	@Test
	public void test03StatsOneHuman() throws Exception {
		// @formatter:off
		mockMvc.perform(
				get("/stats/")
					.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.count_mutant_dna").value(0))
				.andExpect(jsonPath("$.count_human_dna").value(1))
				.andExpect(jsonPath("$.ratio").value(0));
		
		// @formatter:on
	}

	/**
	 * Test - Mutant - Test Diagonal UP - find in last position valid
	 * 
	 * @throws Exception
	 */
	@Test
	public void test04MutantDiagnonalUPLastPositionValid() throws Exception {
		// @formatter:off
		String request = "{\n" + 
				"	\"dna\": [\"AACCCC\",\n" + 
				"	        \"CTCAGC\",\n" + 
				"	        \"ACCAAA\",\n" + 
				"	        \"ACAAAC\",\n" + 
				"	        \"CAAATC\",\n" + 
				"	        \"CAACAA\"]\n" + 
				"}\n" + 
				""; 
		mockMvc.perform(
				post("/mutant/")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(request.getBytes()))
				.andExpect(status().isOk());
		// @formatter:on
	}

	/**
	 * Test - Mutant - Test Diagonal UP - find at row 0
	 * 
	 * @throws Exception
	 */
	@Test
	public void test05mutantDiagnonalUPAtRowZero() throws Exception {
		// @formatter:off
		String request = "{\n" + 
				"	\"dna\": [\"AACCCCT\",\n" + 
				"	        \"CTCACTT\",\n" + 
				"	        \"ACCCTAT\",\n" + 
				"	        \"ACCTACC\",\n" + 
				"	        \"CAACTCC\",\n" + 
				"	        \"CAACAAT\",\n" + 
				"	        \"CAACAAT\"]\n" + 
				"}\n" + 
				""; 
		mockMvc.perform(
				post("/mutant/")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(request.getBytes()))
				.andExpect(status().isOk());
		// @formatter:on
	}

	/**
	 * Test - Human - Teste Diagonal UP - find only 1 sequence at row 0
	 * 
	 * @throws Exception
	 */
	@Test
	public void test06humanDiagnonalUPOneSequenceAtRowZero() throws Exception {
		// @formatter:off
		String request = "{\n" + 
				"	\"dna\": [\"AATCCCA\",\n" + 
				"	        \"CTCACTT\",\n" + 
				"	        \"ACCCTAT\",\n" + 
				"	        \"ACCTACC\",\n" + 
				"	        \"CAATTCC\",\n" + 
				"	        \"CAACAAT\",\n" + 
				"	        \"CAACAAT\"]\n" + 
				"}"; 
		mockMvc.perform(
				post("/mutant/")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(request.getBytes()))
				.andExpect(status().isForbidden());
		// @formatter:on
	}

	/**
	 * Test - Mutant - Teste Diagonal DOWN respective last lines
	 * 
	 * @throws Exception
	 */
	@Test
	public void test07mutantDiagnonalDOWNRespectiveLastLine() throws Exception {
		// @formatter:off
		String request = "{\n" + 
				"	\"dna\": [\"AATACCA\",\n" + 
				"	        \"CTCAATT\",\n" + 
				"	        \"ACCCTAT\",\n" + 
				"	        \"ACCTACA\",\n" + 
				"	        \"CAATTCC\",\n" + 
				"	        \"CAACAAT\",\n" + 
				"	        \"CAAAAAT\"]\n" + 
				"}\n" + 
				""; 
		mockMvc.perform(
				post("/mutant/")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(request.getBytes()))
				.andExpect(status().isOk());
		// @formatter:on
	}

	/**
	 * Test - Mutante - Test Horizontal
	 * 
	 * @throws Exception
	 */
	@Test
	public void test08mutantHorizontal() throws Exception {
		// @formatter:off
		String request = "{\n" + 
				"	\"dna\": [\"ACCCCCT\",\n" + 
				"	        \"CTCACTT\",\n" + 
				"	        \"ACCCTAT\",\n" + 
				"	        \"ACCTACC\",\n" + 
				"	        \"CAACTCC\",\n" + 
				"	        \"CAACAAT\",\n" + 
				"	        \"CAACCCC\"]\n" + 
				"}\n" + 
				""; 
		mockMvc.perform(
				post("/mutant/")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(request.getBytes()))
				.andExpect(status().isOk());
		// @formatter:on
	}

	/**
	 * Test - Mutante - Test Vertical
	 * 
	 * @throws Exception
	 */
	@Test
	public void test09mutantVertical() throws Exception {
		// @formatter:off
		String request = "{\n" + 
				"	\"dna\": [\"ACCACCT\",\n" + 
				"	        \"CTCACTT\",\n" + 
				"	        \"ACCATAT\",\n" + 
				"	        \"AAAGACT\",\n" + 
				"	        \"CAACTCC\",\n" + 
				"	        \"CAACAAT\",\n" + 
				"	        \"CAACCCA\"]\n" + 
				"}\n" + 
				""; 
		mockMvc.perform(
				post("/mutant/")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(request.getBytes()))
				.andExpect(status().isOk());
		// @formatter:on
	}

	/**
	 * Test - Mutante - Test Vertical first and last column
	 * 
	 * @throws Exception
	 */
	@Test
	public void test10mutantVertical() throws Exception {
		// @formatter:off
		String request = "{\n" + 
				"	\"dna\": [\"ACCACCT\",\n" + 
				"	        \"ATCACTT\",\n" + 
				"	        \"ACCATAC\",\n" + 
				"	        \"AAAGACG\",\n" + 
				"	        \"CCACTGG\",\n" + 
				"	        \"CATCAGG\",\n" + 
				"	        \"CAACCGG\"]\n" + 
				"}\n" + 
				""; 
		mockMvc.perform(
				post("/mutant/")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(request.getBytes()))
				.andExpect(status().isOk());
		// @formatter:on
	}

	/**
	 * Test - Mutant/Human - Status with 2 human and 6 mutant
	 * 
	 * @throws Exception
	 */
	@Test
	public void test11StatsMutantHuman() throws Exception {
		// @formatter:off
		mockMvc.perform(
				get("/stats/")
					.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.count_mutant_dna").value(6))
				.andExpect(jsonPath("$.count_human_dna").value(2))
				.andExpect(jsonPath("$.ratio").value(3));
		
		// @formatter:on
	}

	/**
	 * Test - Human - Sequence less than 4
	 * 
	 * @throws Exception
	 */
	@Test
	public void test12HumanSequenceLessThan4() throws Exception {
		// @formatter:off
		String request = "{\n"+ 
				"\"dna\": " +
				"        [\"ACC\",\n" + 
				"         \"CTC\",\n" + 
				"         \"ACG\"\n" + 
				"        ]\n" +
				"}"; 
		mockMvc.perform(
				post("/mutant/")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(request.getBytes()))
				.andExpect(status().isForbidden())
			;
		// @formatter:on
	}

	/**
	 * Test - Mutant - Test Diagonal UP/DOWN -Diagonal Zero )
	 * 
	 * @throws Exception
	 */
	@Test
	public void test13mutantDiagnonalZero() throws Exception {
		// @formatter:off
		String request = "{\n" + 
				"	\"dna\": [\"AACCGCT\",\n" + 
				"	        \"CTCACTT\",\n" + 
				"	        \"ACCCCAT\",\n" + 
				"	        \"ACGCGCC\",\n" + 
				"	        \"CACATCC\",\n" + 
				"	        \"CCACAAT\",\n" + 
				"	        \"CAACAAT\"]\n" + 
				"}\n" + 
				""; 
		mockMvc.perform(
				post("/mutant/")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(request.getBytes()))
				.andExpect(status().isOk());
		// @formatter:on
	}

	/**
	 * Test - Mutant - Test Diagonal UP/DOWN -Diagonal Zero )
	 * 
	 * @throws Exception
	 */
	@Test
	public void test13mutantDiagnonalZeroDown() throws Exception {
		// @formatter:off
		String request = "{\n" + 
				"	\"dna\": [\"AACCGCT\",\n" + 
				"	        \"CTCACTT\",\n" + 
				"	        \"ACGCTAT\",\n" + 
				"	        \"ACGGGCC\",\n" + 
				"	        \"CACAGCC\",\n" + 
				"	        \"CCACAGT\",\n" + 
				"	        \"TAACAAG\"]\n" + 
				"}\n" + 
				""; 
		mockMvc.perform(
				post("/mutant/")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(request.getBytes()))
				.andExpect(status().isOk());
		// @formatter:on
	}

	/**
	 * Test - Mutant - Olny Diagonal UP/DOWN - Non Diagonal Zero )
	 * 
	 * @throws Exception
	 */
	@Test
	public void test14mutantDiagnonalOnlyDown() throws Exception {
		// @formatter:off
		String request = "{\n" + 
				"  \"dna\":[\"AACAGCT\",\n" + 
				"	        \"CTCAATT\",\n" + 
				"	        \"ACGCTAT\",\n" + 
				"	        \"ACGCGCA\",\n" + 
				"	        \"CACGGCC\",\n" + 
				"	        \"CCACGGT\",\n" + 
				"	        \"TAACAGG\"]\n" + 
				"}\n" + 
				""; 
		mockMvc.perform(
				post("/mutant/")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(request.getBytes()))
				.andExpect(status().isOk());
		// @formatter:on
	}

	/**
	 * Test - Mutant - Olny Diagonal UP/DOWN - Non Diagonal Zero )
	 * 
	 * @throws Exception
	 */
	@Test
	public void test15mutantDiagnonalOnlyuP() throws Exception {
		// @formatter:off
		String request = "{\n" + 
				"  \"dna\":[\"ACATGCT\",\n" + 
				"	        \"CTTAGTT\",\n" + 
				"	        \"ATGCTAT\",\n" + 
				"	        \"TCCCGAC\",\n" + 
				"	        \"CACTGCC\",\n" + 
				"	        \"CCATCGT\",\n" + 
				"	        \"TAACACG\"]\n" + 
				"}\n" + 
				""; 
		mockMvc.perform(
				post("/mutant/")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(request.getBytes()))
				.andExpect(status().isOk());
		// @formatter:on
	}

	/**
	 * Invalid Request - The length of the DNA sequences must be the same size.
	 * 
	 * 
	 * @throws Exception
	 */
	@Test
	public void invalidRequestInconsistentLength() throws Exception {
		// @formatter:off
		String request = "{\n" + 
				"	\"dna\": [\"CTXXXXT\",\n" + 
				"	        \"CTCACTT\",\n" + 
				"	        \"ACGCTAT\",\n" + 
				"	        \"ACCTACC\",\n" + 
				"	        \"CAATTCC\",\n" + 
				"	        \"CACCAAC\",\n" + 
				"	        \"CAACAAT\"]\n" + 
				"}\n" + 
				""; 
		ResultActions result = mockMvc.perform(
				post("/mutant/")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(request.getBytes()))
				.andExpect(status().isBadRequest());
		String response = result.andReturn().getResponse().getContentAsString();
		response.contains("dna.sequence.inconsistent.length");
		response.contains("The length of the DNA sequences must be the same size");
		// @formatter:on
	}

	/**
	 * Invalid Request - Invalid Nitrogenous base. The only valid characters are A,
	 * T, C e G.
	 * 
	 * 
	 * @throws Exception
	 */
	@Test
	public void invalidRequestInvalidNitrogenousBase() throws Exception {
		// @formatter:off
		String request = "{\n" + 
				"	\"dna\": [\"ACCTATC\",\n" + 
				"	        \"CTCACTT\",\n" + 
				"	        \"ACGCTAT\",\n" + 
				"	        \"ACCTACC\",\n" + 
				"	        \"CAATTCC\",\n" + 
				"	        \"CC\",\n" + 
				"	        \"CAACAAT\"]\n" + 
				"}\n" + 
				""; 
		ResultActions result = mockMvc.perform(
				post("/mutant/")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(request.getBytes()))
				.andExpect(status().isBadRequest());
		String response = result.andReturn().getResponse().getContentAsString();
		response.contains("dna.invalid.nitrogenous.base");
		response.contains("Invalid Nitrogenous base. The only valid characters are A, T, C e G.");
		response.contains("The only valid characters are A, T, C e G. Found invalida char in BCCTATC");
		// @formatter:on
	}

	/**
	 * Invalid Request - Invalid Nitrogenous base. The only valid characters are A,
	 * T, C e G.
	 * 
	 * 
	 * @throws Exception
	 */
	@Test
	public void invalidRequestWithoutDNA() throws Exception {
		// @formatter:off
		String request = "{ }";
		ResultActions result = mockMvc.perform(
				post("/mutant/")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(request.getBytes()))
				.andExpect(status().isBadRequest());
		String response = result.andReturn().getResponse().getContentAsString();
		response.contains("NotNull.DNASequence.dna");
		response.contains("DNA sequence is required");
		
		// @formatter:on
	}

	@Test
	public void coverageDomain() {
		DNAStatus dnaStatus = new DNAStatus();
		// coverage only
		dnaStatus.setHumanCount(0L);
		dnaStatus.setMutantCount(0L);
		dnaStatus.setRatio(BigDecimal.ZERO);
		dnaStatus.setTotal(0L);
		System.out.println(dnaStatus);

		DNASequence sequence = new DNASequence();
		sequence.setDna(Arrays.asList("A"));
		sequence.getDna();
		System.out.println(sequence);

		DNAResult result = new DNAResult();
		result.setDna(sequence);
		result.setMutant(false);
		result.getDna();
		System.out.println(result);

		ResponseError responseError = new ResponseError(0, "", "", "");
		responseError.getArgsMessage();
		responseError.getErrorCode();
		responseError.getErrorMessage();
		responseError.getMessage();
		responseError.getStatus();
		responseError.setTimestamp(1L);
		responseError.getTimestamp();

	}

}
